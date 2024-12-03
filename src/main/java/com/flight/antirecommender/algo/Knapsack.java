package com.flight.antirecommender.algo;

import com.flight.antirecommender.entity.CandidateWithLimits;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FLIGHT
 */
public class Knapsack {

  public static List<CandidateWithLimits> knapsack(
    List<CandidateWithLimits> candidates,
    int memoryLimit,
    int timeLimit)
  {
    int n = candidates.size();

    int[][][] dp = new int[n + 1][memoryLimit + 1][timeLimit + 1];

    for (int i = 1; i <= n; i++) {
      for (int w = 0; w <= memoryLimit; w++) {
        for (int v = 0; v <= timeLimit; v++) {
          CandidateWithLimits candidate = candidates.get(i - 1);
          if (candidate.memory() <= w && candidate.time() <= v) {
            dp[i][w][v] = Math.max(
              candidate.score() + dp[i - 1][w - candidate.memory()][v - candidate.time()],
              dp[i - 1][w][v]
            );
          } else {
            dp[i][w][v] = dp[i - 1][w][v];
          }
        }
      }
    }

    List<CandidateWithLimits> itemsIncluded = new ArrayList<>();
    int w = memoryLimit;
    int v = timeLimit;

    for (int i = n; i > 0; i--) {
      if (dp[i][w][v] != dp[i - 1][w][v]) {
        CandidateWithLimits candidate = candidates.get(i - 1);
        itemsIncluded.add(candidates.get(i - 1));
        w -= candidate.memory();
        v -= candidate.time();
      }
    }

    return itemsIncluded;
  }
}
