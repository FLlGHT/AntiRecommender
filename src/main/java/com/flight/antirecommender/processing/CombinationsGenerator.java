package com.flight.antirecommender.processing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FLIGHT
 */
public class CombinationsGenerator {

  private static final Logger logger = LoggerFactory.getLogger(CombinationsGenerator.class);

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
      if (combinations.size() % 1_000_000 == 0) {
        logger.info("{}m combinations generated...", combinations.size() / 1_000_000);
      }
      return;
    }

    for (int i = start; i < elements.length; i++) {
      currentCombination.add(elements[i]);
      generateCombinations(elements, combinationSize, i + 1, currentCombination, combinations);
      currentCombination.remove(currentCombination.size() - 1);
    }
  }
}
