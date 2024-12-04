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
import java.util.Optional;

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
    List<StepsAndMetrics> afterCandidatesSelection = selectCandidates();
    logger.info("{} candidates after candidates selection", afterCandidatesSelection.size());

    int batchSize = AntiRecommenderConfig.BATCH_SIZE;
    int batchCount = (afterCandidatesSelection.size() + batchSize - 1) / batchSize;
    logger.info("batch size: {}, batch count: {}", batchSize, batchCount);
    List<StepsAndScore> bestFromEachBatch = new ArrayList<>();

    for (int batchNumber = 0; batchNumber < batchCount; ++batchNumber) {
      int fromIndex = batchNumber * batchSize;
      int toIndex = Math.min(fromIndex + batchSize, afterCandidatesSelection.size());
      List<StepsAndMetrics> candidatesToProcess = afterCandidatesSelection.subList(fromIndex, toIndex);
      List<StepsAndScore> afterFeatureSelectionInBatch = selectFeatures(candidatesToProcess);
      Optional<StepsAndScore> bestFitsInBatch = selectFirstFits(afterFeatureSelectionInBatch);
      bestFitsInBatch.ifPresent(bestFromEachBatch::add);
      logger.info("{} batch processed", batchNumber + 1);
    }

    return bestFromEachBatch.stream()
      .sorted(Comparator.comparing(StepsAndScore::score).reversed())
      .toList();
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
    FeatureOptions[] featurePool = FeatureOptions.activeFeatureOptions;
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

  private Optional<StepsAndScore> selectFirstFits(List<StepsAndScore> candidates) {
    for (StepsAndScore candidate : candidates) {
      int candidateMemory = 0, candidateTime = 0;
      for (CandidateOptions appliedCandidate : candidate.appliedCandidates()) {
        candidateMemory += appliedCandidate.getMemory();
        candidateTime += appliedCandidate.getTime();
      }
      int featureMemory = 0, featureTime = 0;
      for (FeatureOptions appliedFeature : candidate.appliedFeatures()) {
        featureMemory += appliedFeature.getMemory();
        featureTime += appliedFeature.getTime();
      }

      boolean fitsByMemory = candidateMemory + featureMemory <= AntiRecommenderConfig.MEMORY_LIMIT;
      boolean fitsByTime = candidateTime + featureTime <= AntiRecommenderConfig.TIME_LIMIT;

      if (fitsByMemory && fitsByTime)
        return Optional.of(candidate);
    }
    return Optional.empty();
  }
}
