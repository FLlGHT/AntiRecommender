package com.flight.antirecommender.entity;

import java.util.Comparator;
import java.util.List;

/**
 * @author FLIGHT
 */
public record Step(List<String> selectedOptions) {

  public static <T extends WithTitle & WithId> Step createFrom(List<T> options) {
    return new Step(options.stream()
      .sorted(Comparator.comparing(WithId::getId))
      .map(T::getTitle)
      .toList()
    );
  }

  @Override
  public String toString() {
    return "Step{" +
      "selectedOptions=" + selectedOptions +
      '}';
  }
}
