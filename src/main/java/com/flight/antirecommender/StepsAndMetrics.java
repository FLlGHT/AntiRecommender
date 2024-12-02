package com.flight.antirecommender;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author FLIGHT
 */
public record StepsAndMetrics(Steps steps, Map<Metric, MetricSumAndCoefficient> metricSumAndCoefficient) {

  public StepsAndMetrics copy() {
    Map<Metric, MetricSumAndCoefficient> metricsCopy = new HashMap<>();

    for (Map.Entry<Metric, MetricSumAndCoefficient> metricMetricSumAndCoefficientEntry : metricSumAndCoefficient.entrySet()) {
      metricsCopy.put(metricMetricSumAndCoefficientEntry.getKey(), metricMetricSumAndCoefficientEntry.getValue().copy());
    }

    return new StepsAndMetrics(
      steps.copy(),
      metricsCopy);
  }

  public static StepsAndMetrics empty() {
    return new StepsAndMetrics(new Steps(new ArrayList<>()), new HashMap<>());
  }

  public void applyCandidates(List<CandidateOptions> candidateOptions) {
    Step step = Step.createFrom(candidateOptions);
    steps.addStep(step);

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

    for (FeatureOptions featureOption : featureOptions) {
      List<MetricAndCoefficientDelta> metricAndCoefficientDeltaList = featureOption.getMetricAndCoefficientDelta();
      for (MetricAndCoefficientDelta metricAndCoefficientDelta : metricAndCoefficientDeltaList) {
        MetricSumAndCoefficient metricResults = metricSumAndCoefficient.computeIfAbsent(metricAndCoefficientDelta.metric(),
          unused -> MetricSumAndCoefficient.empty()
        );
        metricResults.update(metricAndCoefficientDelta.coefficientDelta());
      }
    }
  }

  public int toScore() {
    return metricSumAndCoefficient.values().stream()
      .map(sumAndCoefficient -> sumAndCoefficient.getCoefficient() * sumAndCoefficient.getSum())
      .map(Math::floor)
      .map(Double::intValue)
      .reduce(0, Integer::sum);
  }
}
