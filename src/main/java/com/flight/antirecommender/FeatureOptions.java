package com.flight.antirecommender;

import java.util.List;

/**
 * @author FLIGHT
 */
public enum FeatureOptions implements WithTitle, WithId {

  SIMILAR_VIEWS("Похожие просмотры", List.of(
    MetricAndCoefficientDelta.of(Metric.RELEVANCE, 1.1),
    MetricAndCoefficientDelta.of(Metric.VIEW_TIME, 1.2)
  )),

  CLICKBAIT("Кликбейт", List.of(
    MetricAndCoefficientDelta.of(Metric.RELEVANCE, 0.9),
    MetricAndCoefficientDelta.of(Metric.VIEW_TIME, 1.5)
  )),

  INVOLVEMENT("Вовлеченность", List.of(
    MetricAndCoefficientDelta.of(Metric.LIKES, 1.3)
  )),

  FRIENDS_AND_POPULARITY("Друзья и популярность", List.of(
    MetricAndCoefficientDelta.of(Metric.CLICKS, 1.1)
  )),

  STRONG_PERSONAL_PREFERENCES("Сильные личные предпочтения", List.of(
    MetricAndCoefficientDelta.of(Metric.AUTHORS_SATISFACTION, 1.2)
  )),

  NOVELTY_OF_CONTENT("Учет новизны контента", List.of(
    MetricAndCoefficientDelta.of(Metric.LIKES, 1.1),
    MetricAndCoefficientDelta.of(Metric.VIEW_TIME, 0.8)
  )),

  SOCIAL_SIGNIFICANCE("Социальная значимость", List.of(
    MetricAndCoefficientDelta.of(Metric.AUTHORS_SATISFACTION, 1.2)
  )),

  REGIONAL_PREFERENCES("Региональные предпочтения", List.of(
    MetricAndCoefficientDelta.of(Metric.RELEVANCE, 1.5),
    MetricAndCoefficientDelta.of(Metric.CLICKS, 0.8)
  )),

  LOCAL_NEWS("Локальные новости", List.of(
    MetricAndCoefficientDelta.of(Metric.LIKES, 1.3),
    MetricAndCoefficientDelta.of(Metric.CLICKS, 1.1)
  )),

  AESTHETIC_PREFERENCES("Эстетические предпочтения", List.of(
    MetricAndCoefficientDelta.of(Metric.LIKES, 0.9),
    MetricAndCoefficientDelta.of(Metric.VIEW_TIME, 1.2)
  ))
  ;

  private final String title;

  private final List<MetricAndCoefficientDelta> metricAndCoefficientDelta;

  FeatureOptions(String title, List<MetricAndCoefficientDelta> metricAndCoefficientDelta) {
    this.title = title;
    this.metricAndCoefficientDelta = metricAndCoefficientDelta;
  }

  public String getTitle() {
    return title;
  }

  public List<MetricAndCoefficientDelta> getMetricAndCoefficientDelta() {
    return metricAndCoefficientDelta;
  }

  @Override
  public int getId() {
    return this.ordinal();
  }
}
