package com.flight.antirecommender.entity;

import com.flight.antirecommender.data.CandidateOptions;
import com.flight.antirecommender.data.FeatureOptions;

import java.util.*;

/**
 * @author FLIGHT
 */
public record StepsAndMetrics(
  Steps steps,
  Set<CandidateOptions> appliedCandidates,
  Set<FeatureOptions> appliedFeatures,
  Map<Metric, MetricSumAndCoefficient> metricSumAndCoefficient)
{

  public StepsAndMetrics copy() {
    Map<Metric, MetricSumAndCoefficient> metricsCopy = new HashMap<>();

    for (Map.Entry<Metric, MetricSumAndCoefficient> metricMetricSumAndCoefficientEntry : metricSumAndCoefficient.entrySet()) {
      metricsCopy.put(metricMetricSumAndCoefficientEntry.getKey(), metricMetricSumAndCoefficientEntry.getValue().copy());
    }

    return new StepsAndMetrics(
      steps.copy(),
      new HashSet<>(appliedCandidates),
      new HashSet<>(appliedFeatures),
      metricsCopy);
  }

  public static StepsAndMetrics empty() {
    return new StepsAndMetrics(
      new Steps(new ArrayList<>()),
      new HashSet<>(),
      new HashSet<>(),
      new HashMap<>()
    );
  }

  public void applyCandidates(List<CandidateOptions> candidateOptions) {
    Step step = Step.createFrom(candidateOptions);
    steps.addStep(step);
    appliedCandidates.addAll(candidateOptions);

    for (CandidateOptions candidateOption : candidateOptions) {
      List<MetricAndSumDelta> metricAndSumDeltaList = candidateOption.getMetricAndSumDelta();
      for (MetricAndSumDelta metricAndSumDelta : metricAndSumDeltaList) {
        MetricSumAndCoefficient metricResults = metricSumAndCoefficient.computeIfAbsent(
          metricAndSumDelta.metric(),
          unused -> MetricSumAndCoefficient.empty()
        );
        metricResults.update(metricAndSumDelta.sumDelta());
      }
    }
  }

  public void applyFeatures(List<FeatureOptions> featureOptions) {
    Step step = Step.createFrom(featureOptions);
    steps.addStep(step);
    appliedFeatures.addAll(featureOptions);

    for (FeatureOptions featureOption : featureOptions) {
      List<MetricAndCoefficientDelta> metricAndCoefficientDeltaList = featureOption.getMetricAndCoefficientDelta();
      for (MetricAndCoefficientDelta metricAndCoefficientDelta : metricAndCoefficientDeltaList) {
        MetricSumAndCoefficient metricResults = metricSumAndCoefficient.computeIfAbsent(
          metricAndCoefficientDelta.metric(),
          unused -> MetricSumAndCoefficient.empty()
        );
        metricResults.update(metricAndCoefficientDelta.coefficientDelta());
      }
    }
  }

  public void applyCombinations(List<MetricAndSumDelta> metricAndSumDeltaList) {
    for (MetricAndSumDelta metricAndSumDelta : metricAndSumDeltaList) {
      MetricSumAndCoefficient metricResult = metricSumAndCoefficient.computeIfAbsent(
        metricAndSumDelta.metric(),
        unused -> MetricSumAndCoefficient.empty()
      );

      metricResult.update(metricAndSumDelta.sumDelta());
    }
  }

  public int toScore() {
    double score = metricSumAndCoefficient.values().stream()
      .map(sumAndCoefficient -> sumAndCoefficient.getSum() * sumAndCoefficient.getCoefficient())
      .reduce(0.0, Double::sum);

    return (int) Math.round(score);
  }
}
