package com.flight.antirecommender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author FLIGHT
 */

public class ScoreComputer {

  private static final Logger logger = LoggerFactory.getLogger(ScoreComputer.class);

  private final int CANDIDATES_SELECTION_LIMIT = 10;

  private final int AFTER_CANDIDATES_SELECTION_LIMIT = 500;

  private final int FEATURE_SELECTION_LIMIT = 3;

  private final int AFTER_FEATURE_SELECTION_LIMIT = 500;

  public StepsAndScore findWinner() {
    List<StepsAndMetrics> candidates = findCandidates();
    StepsAndScore winner = findWinner(candidates);

    logger.info("Winner: {}", winner.toString());

    return winner;
  }

  private List<StepsAndMetrics> findCandidates() {
    // 1 step
    List<StepsAndMetrics> afterCandidatesSelection = selectCandidates();
    logger.info("{} candidates after candidates selection", afterCandidatesSelection.size());

    // 2 step
    List<StepsAndMetrics> afterFeatureSelection = selectFeatures(afterCandidatesSelection);
    logger.info("{} candidates after technical limitations", afterFeatureSelection.size());

    // 3 step
    List<StepsAndMetrics> afterTechnicalLimitations = selectTechnicalLimitations();
    logger.info("{} candidates after technical limitations", afterTechnicalLimitations.size());

    return afterTechnicalLimitations;
  }

  private List<StepsAndMetrics> selectCandidates() {
    CandidateOptions[] candidatePool = CandidateOptions.values();
    List<List<CandidateOptions>> combinations = CombinationsGenerator.generateCombinations(
      candidatePool,
      CANDIDATES_SELECTION_LIMIT
    );

    List<StepsAndMetrics> candidateStepsAndMetrics = new ArrayList<>();
    for (List<CandidateOptions> combination : combinations) {
      StepsAndMetrics stepsAndMetrics = StepsAndMetrics.empty();
      stepsAndMetrics.applyCandidates(combination);
      candidateStepsAndMetrics.add(stepsAndMetrics);
    }

    List<StepsAndMetrics> limitedCandidates = candidateStepsAndMetrics.stream()
      .sorted(Comparator.comparing(StepsAndMetrics::toScore).reversed())
      .limit(AFTER_CANDIDATES_SELECTION_LIMIT)
      .toList();

    logger.info("limited candidates size: {}", limitedCandidates.size());
    return limitedCandidates;
  }

  private List<StepsAndMetrics> selectFeatures(List<StepsAndMetrics> candidates) {
    FeatureOptions[] featurePool = FeatureOptions.values();
    List<List<FeatureOptions>> combinations = CombinationsGenerator.generateCombinations(
      featurePool,
      FEATURE_SELECTION_LIMIT
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
      .sorted(Comparator.comparing(StepsAndMetrics::toScore).reversed())
      .limit(AFTER_FEATURE_SELECTION_LIMIT)
      .map(i -> new StepsAndScore(i.steps(), i.toScore()))
      .toList();

    logger.info("limited features size: {}", limitedCandidatesWithFeatures.size());
    return new ArrayList<>();
  }

  private List<StepsAndMetrics> selectTechnicalLimitations() {
    return new ArrayList<>();
  }

  private StepsAndScore findWinner(List<StepsAndMetrics> candidates) {
    return candidates.stream()
      .map(candidate -> new StepsAndScore(candidate.steps(), candidate.toScore()))
      .max(Comparator.comparing(StepsAndScore::score)).orElseThrow(RuntimeException::new);
  }
}
