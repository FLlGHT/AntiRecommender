package com.flight.antirecommender.entity;

import com.flight.antirecommender.data.CandidateOptions;
import com.flight.antirecommender.data.FeatureOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FLIGHT
 */
public record CandidateFeatureCombination(
  CandidateOptions candidateOption,
  FeatureOptions featureOption,
  List<MetricAndSumDelta> metricAndSumDelta) {

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private CandidateOptions candidateOption;
    private FeatureOptions featureOption;
    private final List<MetricAndSumDelta> rewards;

    public Builder() {
      this.rewards = new ArrayList<>();
    }

    public CandidateFeatureCombination.Builder addCandidate(CandidateOptions candidateOption) {
      this.candidateOption = candidateOption;
      return this;
    }

    public CandidateFeatureCombination.Builder addFeature(FeatureOptions featureOption) {
      this.featureOption = featureOption;
      return this;
    }

    public CandidateFeatureCombination.Builder addReward(MetricAndSumDelta reward) {
      rewards.add(reward);
      return this;
    }

    public CandidateFeatureCombination build() {
      return new CandidateFeatureCombination(
        candidateOption,
        featureOption,
        rewards);
    }
  }
}
