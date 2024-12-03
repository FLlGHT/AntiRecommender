package com.flight.antirecommender.entity;

/**
 * @author FLIGHT
 */
public record MetricAndCoefficientDelta(Metric metric, double coefficientDelta) {

  public static MetricAndCoefficientDelta of(Metric metric, double coefficientDelta) {
    return new MetricAndCoefficientDelta(metric, coefficientDelta);
  }
}
