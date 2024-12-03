package com.flight.antirecommender.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FLIGHT
 */
public record Steps(List<Step> steps) {

  public Steps copy() {
    return new Steps(new ArrayList<>(steps));
  }

  @Override
  public String toString() {
    return "Steps{" +
      "steps=" + steps +
      '}';
  }

  public void addStep(Step step) {
    steps.add(step);
  }
}
