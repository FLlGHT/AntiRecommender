package com.flight.antirecommender.entity;

import com.flight.antirecommender.data.CandidateOptions;

import java.util.*;

/**
 * @author FLIGHT
 */
public record CandidateCombination(Set<CandidateOptions> сandidateOptions, List<MetricAndSumDelta> reward) {

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private final Set<CandidateOptions> options;
    private final List<MetricAndSumDelta> rewards;

    public Builder() {
      this.options = new HashSet<>();
      this.rewards = new ArrayList<>();
    }

    public Builder addOption(CandidateOptions option) {
      options.add(option);
      return this;
    }

    public Builder addReward(MetricAndSumDelta reward) {
      rewards.add(reward);
      return this;
    }

    public CandidateCombination build() {
      return new CandidateCombination(options, rewards);
    }
  }
}
