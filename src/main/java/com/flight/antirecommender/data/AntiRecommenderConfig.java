package com.flight.antirecommender.data;

/**
 * @author FLIGHT
 */
public class AntiRecommenderConfig {

  public static final int CANDIDATES_SELECTION_LIMIT = 8;

  public static final int BATCH_SIZE = 5000;

  public static final int AFTER_CANDIDATES_SELECTION_LIMIT = 1_000_000;

  public static final int FEATURE_SELECTION_LIMIT = 3;

  public static final int AFTER_FEATURE_SELECTION_LIMIT = 50;

  public static final int MEMORY_LIMIT = 32;

  public static final int TIME_LIMIT = 400;

}
