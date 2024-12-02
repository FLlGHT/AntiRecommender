package com.flight.antirecommender;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FLIGHT
 */
public class CombinationsGenerator {

  public static <T> List<List<T>> generateCombinations(T[] elements, int combinationSize) {
    List<List<T>> combinations = new ArrayList<>();
    generateCombinations(elements, combinationSize, 0, new ArrayList<>(), combinations);
    return combinations;
  }

  private static <T> void generateCombinations(
    T[] elements,
    int combinationSize,
    int start,
    List<T> currentCombination,
    List<List<T>> combinations)
  {
    if (currentCombination.size() == combinationSize) {
      combinations.add(new ArrayList<>(currentCombination));
      return;
    }

    for (int i = start; i < elements.length; i++) {
      currentCombination.add(elements[i]);
      generateCombinations(elements, combinationSize, i + 1, currentCombination, combinations);
      currentCombination.remove(currentCombination.size() - 1);
    }
  }
}
