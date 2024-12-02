package com.flight.antirecommender;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author FLIGHT
 */

@Configuration
public class AntiRecommenderContext {

  @Bean
  public ScoreComputer scoreComputer() {
    return new ScoreComputer();
  }
}
