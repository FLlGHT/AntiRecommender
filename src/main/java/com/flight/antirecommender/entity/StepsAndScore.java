package com.flight.antirecommender.entity;

import com.flight.antirecommender.data.CandidateOptions;
import com.flight.antirecommender.data.FeatureOptions;

import java.util.Set;

/**
 * @author FLIGHT
 */
public record StepsAndScore(
  Steps steps,
  Set<CandidateOptions> appliedCandidates,
  Set<FeatureOptions> appliedFeatures,
  int score) {

}
