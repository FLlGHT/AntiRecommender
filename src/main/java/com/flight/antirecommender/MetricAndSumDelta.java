package com.flight.antirecommender;

/**
 * @author FLIGHT
 */
public record MetricAndSumDelta(Metric metric, int sumDelta) {

  public static MetricAndSumDelta of(Metric metric, int sumDelta) {
    return new MetricAndSumDelta(metric, sumDelta);
  }
}
