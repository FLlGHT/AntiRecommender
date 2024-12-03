package com.flight.antirecommender;

import com.flight.antirecommender.processing.AntiRecommenderProcessor;
import com.flight.antirecommender.processing.CombinationsApplier;
import com.flight.antirecommender.processing.ScoreComputer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author FLIGHT
 */

@Configuration
public class AntiRecommenderContext {

  @Bean
  public CombinationsApplier combinationsApplier() {
    return new CombinationsApplier();
  }
  @Bean
  public ScoreComputer scoreComputer(CombinationsApplier combinationsApplier) {
    return new ScoreComputer(combinationsApplier);
  }
  @Bean
  public AntiRecommenderProcessor antiRecommenderProcessor(ScoreComputer scoreComputer) {
    return new AntiRecommenderProcessor(scoreComputer);
  }
}
