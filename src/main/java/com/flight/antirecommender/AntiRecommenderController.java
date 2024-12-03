package com.flight.antirecommender;

import com.flight.antirecommender.entity.StepsAndScore;
import com.flight.antirecommender.processing.AntiRecommenderProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author FLIGHT
 */

@RestController
public class AntiRecommenderController {

  private final AntiRecommenderProcessor antiRecommenderProcessor;

  public AntiRecommenderController(AntiRecommenderProcessor antiRecommenderProcessor) {
    this.antiRecommenderProcessor = antiRecommenderProcessor;
  }

  @GetMapping("/compute")
  public List<StepsAndScore> compute() {
    return antiRecommenderProcessor.findTopScored();
  }
}
