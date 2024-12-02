package com.flight.antirecommender;

import java.util.List;

/**
 * @author FLIGHT
 */
public enum CandidateOptions implements WithTitle, WithId {

  SHOWS("Развлекательные шоу", List.of(
    MetricAndSumDelta.of(Metric.LIKES, 5),
    MetricAndSumDelta.of(Metric.VIEW_TIME, 15))
  ),

  GAME_VIDEOS("Игровые видео", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 5),
    MetricAndSumDelta.of(Metric.CLICKS, 10)
  )),

  REVIEW_VIDEOS("Обзорные видео", List.of(
    MetricAndSumDelta.of(Metric.VIEW_TIME, 5),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 5)
  )),

  TRAVELLING("Контент с путешествиями", List.of(
    MetricAndSumDelta.of(Metric.LIKES, -5),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 10)
  )),

  COMEDY("Комедийные видео", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 10),
    MetricAndSumDelta.of(Metric.LIKES, 20)
  )),

  DOCUMENTARIES("Документальные фильмы", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 15),
    MetricAndSumDelta.of(Metric.CLICKS, -5)
  )),

  LESSONS("Уроки и семинары", List.of(
    MetricAndSumDelta.of(Metric.VIEW_TIME, 10),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 10)
  )),

  PROFESSIONAL("Профессиональный контент", List.of(
    MetricAndSumDelta.of(Metric.LIKES, 5),
    MetricAndSumDelta.of(Metric.CLICKS, 5)
  )),

  THEMATIC("Тематическое видео", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 10),
    MetricAndSumDelta.of(Metric.VIEW_TIME, -10)
  )),

  GRADE_BASED("Рекомендации на основе оценок", List.of(
    MetricAndSumDelta.of(Metric.LIKES, 5),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 10)
  )),

  CATEGORIES("Контент по категориям", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 10),
    MetricAndSumDelta.of(Metric.VIEW_TIME, 5)
  )),

  TRENDS("Актуальные тренды", List.of(
    MetricAndSumDelta.of(Metric.CLICKS, 15),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, -5)
  )),

  CREATIVE("Креативный контент", List.of(
    MetricAndSumDelta.of(Metric.LIKES, 5),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 5)
  )),

  ARCHIVE("Архивы старых видео", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 15),
    MetricAndSumDelta.of(Metric.CLICKS, 5)
  )),

  PATTERN_SEARCH("Поиск по шаблонам", List.of(
    MetricAndSumDelta.of(Metric.LIKES, -5),
    MetricAndSumDelta.of(Metric.VIEW_TIME, 10)
  )),

  HISTORIC("Исторический контент", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 15),
    MetricAndSumDelta.of(Metric.LIKES, -10)
  )),

  CREATIVE_NEW("Новинки креативов", List.of(
    MetricAndSumDelta.of(Metric.LIKES, 10),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 5)
  )),

  EVENTS_REVIEW("Обзор событий", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 10),
    MetricAndSumDelta.of(Metric.VIEW_TIME, 5)
  )),

  TECHNO("Технологический контент", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 15),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 5)
  ))

  ;

  private final String title;

  private final List<MetricAndSumDelta> metricAndSumDelta;

  CandidateOptions(String title, List<MetricAndSumDelta> metricAndSumDelta) {
    this.title = title;
    this.metricAndSumDelta = metricAndSumDelta;
  }

  public String getTitle() {
    return title;
  }



  public List<MetricAndSumDelta> getMetricAndSumDelta() {
    return metricAndSumDelta;
  }

  @Override
  public int getId() {
    return this.ordinal();
  }
}
