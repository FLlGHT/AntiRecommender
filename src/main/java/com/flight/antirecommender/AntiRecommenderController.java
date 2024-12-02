package com.flight.antirecommender;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FLIGHT
 */

@RestController
public class AntiRecommenderController {

  private final ScoreComputer scoreComputer;

  public AntiRecommenderController(ScoreComputer scoreComputer) {
    this.scoreComputer = scoreComputer;
  }

  @GetMapping("/compute")
  public String compute() {
    return scoreComputer.findWinner().toString();
  }
}
