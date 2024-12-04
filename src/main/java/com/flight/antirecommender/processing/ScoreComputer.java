package com.flight.antirecommender.processing;

import com.flight.antirecommender.entity.StepsAndMetrics;
import com.flight.antirecommender.entity.StepsAndScore;
import com.flight.antirecommender.processing.CombinationsApplier;

/**
 * @author FLIGHT
 */
public class ScoreComputer {

  private final CombinationsApplier combinationsApplier;

  public ScoreComputer(CombinationsApplier combinationsApplier) {
    this.combinationsApplier = combinationsApplier;
  }

  public StepsAndScore compute(StepsAndMetrics stepsAndMetrics) {
    combinationsApplier.apply(stepsAndMetrics);
    double score = stepsAndMetrics.metricSumAndCoefficient().values().stream()
      .map(sumAndCoefficient -> sumAndCoefficient.getSum() * sumAndCoefficient.getCoefficient())
      .reduce(0.0, Double::sum);

    return new StepsAndScore(
      stepsAndMetrics.steps(),
      stepsAndMetrics.appliedCandidates(),
      stepsAndMetrics.appliedFeatures(),
      (int) Math.round(score)
    );
  }
}
