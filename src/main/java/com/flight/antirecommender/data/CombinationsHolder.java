package com.flight.antirecommender.data;

import com.flight.antirecommender.entity.CandidateCombination;
import com.flight.antirecommender.entity.CandidateFeatureCombination;
import com.flight.antirecommender.entity.MetricAndSumDelta;
import com.flight.antirecommender.entity.Metric;

import java.util.List;

/**
 * @author FLIGHT
 */
public class CombinationsHolder {

  private static final List<CandidateCombination> candidateCombinations =
    List.of(
      // 1
      CandidateCombination.builder()
        .addOption(CandidateOptions.WATCHED_AUTHORS)
        .addOption(CandidateOptions.FRIENDS_INTERESTS)
        .addOption(CandidateOptions.SHOWS)
        .addReward(MetricAndSumDelta.of(Metric.LIKES, 15))
        .addReward(MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, -5))
        .build(),

      // 2
      CandidateCombination.builder()
        .addOption(CandidateOptions.CATS)
        .addOption(CandidateOptions.PETS_CONTENT)
        .addReward(MetricAndSumDelta.of(Metric.RELEVANCE, -5))
        .addReward(MetricAndSumDelta.of(Metric.LIKES, 10))
        .build(),

      // 3
      CandidateCombination.builder()
        .addOption(CandidateOptions.POPULAR)
        .addOption(CandidateOptions.GAME_VIDEOS)
        .addReward(MetricAndSumDelta.of(Metric.CLICKS, 20))
        .addReward(MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, -10))
        .build(),

      // 4
      CandidateCombination.builder()
        .addOption(CandidateOptions.POPULAR)
        .addOption(CandidateOptions.DOCUMENTARIES)
        .addReward(MetricAndSumDelta.of(Metric.CLICKS, 20))
        .addReward(MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, -10))
        .build(),

      // 5
      CandidateCombination.builder()
        .addOption(CandidateOptions.NEWS)
        .addOption(CandidateOptions.PETS_CONTENT)
        .addReward(MetricAndSumDelta.of(Metric.LIKES, -5))
        .addReward(MetricAndSumDelta.of(Metric.VIEW_TIME, 10))
        .build(),

      // 6
      CandidateCombination.builder()
        .addOption(CandidateOptions.HISTORIC)
        .addOption(CandidateOptions.COMEDY)
        .addReward(MetricAndSumDelta.of(Metric.RELEVANCE, 5))
        .addReward(MetricAndSumDelta.of(Metric.LIKES, -10))
        .build(),

      // 7 s7 s12
      CandidateCombination.builder()
        .addOption(CandidateOptions.MUSIC_CLIPS)
        .addOption(CandidateOptions.SHOWS)
        .addReward(MetricAndSumDelta.of(Metric.VIEW_TIME, 15))
        .addReward(MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, -5))
        .build(),


      // 8 s8
      CandidateCombination.builder()
        .addOption(CandidateOptions.BOOKS_CONTENT)
        .addOption(CandidateOptions.GRADE_BASED)
        .addReward(MetricAndSumDelta.of(Metric.RELEVANCE, -5))
        .addReward(MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 10))
        .build(),

      // 9 s9
      CandidateCombination.builder()
        .addOption(CandidateOptions.SPORT)
        .addOption(CandidateOptions.GAME_VIDEOS)
        .addReward(MetricAndSumDelta.of(Metric.VIEW_TIME, -10))
        .addReward(MetricAndSumDelta.of(Metric.CLICKS, 15))
        .build(),

      // 10 11 24
      CandidateCombination.builder()
        .addOption(CandidateOptions.SCIENCE_CONTENT)
        .addOption(CandidateOptions.CREATIVE)
        .addReward(MetricAndSumDelta.of(Metric.LIKES, 20))
        .addReward(MetricAndSumDelta.of(Metric.CLICKS, -5))
        .build(),

      // 11 14 29
      CandidateCombination.builder()
        .addOption(CandidateOptions.REVIEW_VIDEOS)
        .addOption(CandidateOptions.EVENTS_REVIEW)
        .addReward(MetricAndSumDelta.of(Metric.RELEVANCE, 10))
        .addReward(MetricAndSumDelta.of(Metric.LIKES, -5))
        .build(),

      // 12
      CandidateCombination.builder()
        .addOption(CandidateOptions.LESSONS)
        .addOption(CandidateOptions.ARCHIVE)
        .addReward(MetricAndSumDelta.of(Metric.VIEW_TIME, -10))
        .addReward(MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 10))
        .build(),

      // 13
      CandidateCombination.builder()
        .addOption(CandidateOptions.CATEGORIES)
        .addOption(CandidateOptions.TECHNO)
        .addReward(MetricAndSumDelta.of(Metric.RELEVANCE, 10))
        .addReward(MetricAndSumDelta.of(Metric.LIKES, -5))
        .build()

      );

  public static List<CandidateFeatureCombination> candidateFeatureCombinations =
    List.of(
      // 1
      CandidateFeatureCombination.builder()
        .addCandidate(CandidateOptions.CATS)
        .addFeature(FeatureOptions.SOCIAL_SIGNIFICANCE)
        .addReward(MetricAndSumDelta.of(Metric.RELEVANCE, -5))
        .addReward(MetricAndSumDelta.of(Metric.LIKES, 10))
        .build(),

      // 2
      CandidateFeatureCombination.builder()
        .addCandidate(CandidateOptions.WATCH_HISTORY)
        .addFeature(FeatureOptions.INVOLVEMENT)
        .addReward(MetricAndSumDelta.of(Metric.RELEVANCE, -15))
        .addReward(MetricAndSumDelta.of(Metric.LIKES, 20))
        .build(),

      // 3
      CandidateFeatureCombination.builder()
        .addCandidate(CandidateOptions.MUSIC_CLIPS)
        .addFeature(FeatureOptions.FRIENDS_AND_POPULARITY)
        .addReward(MetricAndSumDelta.of(Metric.CLICKS, 15))
        .addReward(MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, -10))
        .build(),

      // 4
      CandidateFeatureCombination.builder()
        .addCandidate(CandidateOptions.BOOKS_CONTENT)
        .addFeature(FeatureOptions.LOCAL_NEWS)
        .addReward(MetricAndSumDelta.of(Metric.VIEW_TIME, -5))
        .addReward(MetricAndSumDelta.of(Metric.LIKES, 10))
        .build(),

      // 5
      CandidateFeatureCombination.builder()
        .addCandidate(CandidateOptions.FRIENDS_INTERESTS)
        .addFeature(FeatureOptions.STRONG_PERSONAL_PREFERENCES)
        .addReward(MetricAndSumDelta.of(Metric.LIKES, 10))
        .addReward(MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 10))
        .build(),

      // 6
      CandidateFeatureCombination.builder()
        .addCandidate(CandidateOptions.TRAVELLING)
        .addFeature(FeatureOptions.NOVELTY_OF_CONTENT)
        .addReward(MetricAndSumDelta.of(Metric.VIEW_TIME, 15))
        .addReward(MetricAndSumDelta.of(Metric.CLICKS, -5))
        .build(),

      // 7
      CandidateFeatureCombination.builder()
        .addCandidate(CandidateOptions.PROFESSIONAL)
        .addFeature(FeatureOptions.CLICKBAIT)
        .addReward(MetricAndSumDelta.of(Metric.RELEVANCE, -15))
        .addReward(MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 10))
        .build(),

      // 8
      CandidateFeatureCombination.builder()
        .addCandidate(CandidateOptions.CATEGORIES)
        .addFeature(FeatureOptions.SIMILAR_VIEWS)
        .addReward(MetricAndSumDelta.of(Metric.LIKES, -5))
        .addReward(MetricAndSumDelta.of(Metric.CLICKS, 5))
        .build()
    );

  public static List<CandidateCombination> candidateCombinations() {
    return candidateCombinations;
  }

  public static List<CandidateFeatureCombination> candidateFeatureCombinations() {
    return candidateFeatureCombinations;
  }
}
