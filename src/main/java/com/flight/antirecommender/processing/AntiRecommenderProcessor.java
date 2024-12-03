package com.flight.antirecommender.processing;

import com.flight.antirecommender.data.AntiRecommenderConfig;
import com.flight.antirecommender.data.CandidateOptions;
import com.flight.antirecommender.data.FeatureOptions;
import com.flight.antirecommender.entity.StepsAndMetrics;
import com.flight.antirecommender.entity.StepsAndScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author FLIGHT
 */

public class AntiRecommenderProcessor {

  private static final Logger logger = LoggerFactory.getLogger(AntiRecommenderProcessor.class);

  private final ScoreComputer scoreComputer;

  public AntiRecommenderProcessor(ScoreComputer scoreComputer) {
    this.scoreComputer = scoreComputer;
  }

  public List<StepsAndScore> findTopScored() {
    // 1 step
    List<StepsAndMetrics> afterCandidatesSelection = selectCandidates();
    logger.info("{} candidates after candidates selection", afterCandidatesSelection.size());

    // 2 step
    List<StepsAndScore> afterFeatureSelection = selectFeatures(afterCandidatesSelection);
    logger.info("{} candidates after technical limitations", afterFeatureSelection.size());

    return afterFeatureSelection;
  }

  private List<StepsAndMetrics> selectCandidates() {
    CandidateOptions[] candidatePool = CandidateOptions.activeCandidateSelectors;
    List<List<CandidateOptions>> combinations = CombinationsGenerator.generateCombinations(
      candidatePool,
      AntiRecommenderConfig.CANDIDATES_SELECTION_LIMIT
    );

    List<StepsAndMetrics> candidateStepsAndMetrics = new ArrayList<>();
    for (List<CandidateOptions> combination : combinations) {
      StepsAndMetrics stepsAndMetrics = StepsAndMetrics.empty();
      stepsAndMetrics.applyCandidates(combination);
      candidateStepsAndMetrics.add(stepsAndMetrics);
    }

    List<StepsAndMetrics> limitedCandidates = candidateStepsAndMetrics.stream()
      .sorted(Comparator.comparing(StepsAndMetrics::toScore).reversed())
      .limit(AntiRecommenderConfig.AFTER_CANDIDATES_SELECTION_LIMIT)
      .toList();

    logger.info("limited candidates size: {}", limitedCandidates.size());
    return limitedCandidates;
  }

  private List<StepsAndScore> selectFeatures(List<StepsAndMetrics> candidates) {
    FeatureOptions[] featurePool = FeatureOptions.values();
    List<List<FeatureOptions>> combinations = CombinationsGenerator.generateCombinations(
      featurePool,
      AntiRecommenderConfig.FEATURE_SELECTION_LIMIT
    );

    List<StepsAndMetrics> afterFeaturesApplying = new ArrayList<>();

    for (StepsAndMetrics candidate : candidates) {
      for (List<FeatureOptions> combination : combinations) {
        StepsAndMetrics metricsCopy = candidate.copy();
        metricsCopy.applyFeatures(combination);
        afterFeaturesApplying.add(metricsCopy);
      }
    }

    List<StepsAndScore> limitedCandidatesWithFeatures = afterFeaturesApplying.stream()
      .map(scoreComputer::compute)
      .sorted(Comparator.comparing(StepsAndScore::score).reversed())
      .limit(AntiRecommenderConfig.AFTER_FEATURE_SELECTION_LIMIT)
      .toList();

    logger.info("limited features size: {}", limitedCandidatesWithFeatures.size());
    return limitedCandidatesWithFeatures;
  }
}
