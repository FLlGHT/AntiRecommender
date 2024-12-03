package com.flight.antirecommender.processing;

import com.flight.antirecommender.data.FeatureOptions;
import com.flight.antirecommender.entity.CandidateFeatureCombination;
import com.flight.antirecommender.entity.CandidateCombination;
import com.flight.antirecommender.data.CandidateOptions;
import com.flight.antirecommender.data.CombinationsHolder;
import com.flight.antirecommender.entity.StepsAndMetrics;

import java.util.Set;

/**
 * @author FLIGHT
 */
public class CombinationsApplier {

  public void apply(StepsAndMetrics stepsAndMetrics) {
    for (CandidateCombination candidateSelectorsCandidateCombination : CombinationsHolder.candidateCombinations()) {
      Set<CandidateOptions> options = candidateSelectorsCandidateCombination.сandidateOptions();
      if (stepsAndMetrics.appliedCandidates().containsAll(options)) {
        stepsAndMetrics.applyCombinations(candidateSelectorsCandidateCombination.reward());
      }
    }

    for (CandidateFeatureCombination candidateSelectorsCandidateCombination : CombinationsHolder.candidateFeatureCombinations()) {
      Set<CandidateOptions> appliedCandidates = stepsAndMetrics.appliedCandidates();
      Set<FeatureOptions> appliedFeatures = stepsAndMetrics.appliedFeatures();

      if (appliedCandidates.contains(candidateSelectorsCandidateCombination.candidateOption())
        && appliedFeatures.contains(candidateSelectorsCandidateCombination.featureOption())) {
        stepsAndMetrics.applyCombinations(candidateSelectorsCandidateCombination.reward());
      }
    }
  }
}
