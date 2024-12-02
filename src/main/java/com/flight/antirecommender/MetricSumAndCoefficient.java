package com.flight.antirecommender;

/**
 * @author FLIGHT
 */
public class MetricSumAndCoefficient {

  private int sum;

  private double coefficient;

  public MetricSumAndCoefficient(int sum, double coefficient) {
    this.sum = sum;
    this.coefficient = coefficient;
  }

  public MetricSumAndCoefficient copy() {
    return new MetricSumAndCoefficient(sum, coefficient);
  }

  public void update(int sumDelta) {
    this.sum += sumDelta;
  }

  public void update(double coefficientDelta) {
    this.coefficient *= coefficientDelta;
  }

  public int getSum() {
    return sum;
  }


  public double getCoefficient() {
    return coefficient;
  }

  public static MetricSumAndCoefficient empty() {
    return new MetricSumAndCoefficient(0, 1.0);
  }
}
