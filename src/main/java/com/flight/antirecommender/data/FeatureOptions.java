package com.flight.antirecommender.data;

import com.flight.antirecommender.entity.Metric;
import com.flight.antirecommender.entity.MetricAndCoefficientDelta;
import com.flight.antirecommender.entity.WithId;
import com.flight.antirecommender.entity.WithTitle;

import java.util.List;

/**
 * @author FLIGHT
 */
public enum FeatureOptions implements WithTitle, WithId {

  SIMILAR_VIEWS(1, 25,"Похожие просмотры", List.of(
    MetricAndCoefficientDelta.of(Metric.RELEVANCE, 1.1),
    MetricAndCoefficientDelta.of(Metric.VIEW_TIME, 1.2)
  )),

  CLICKBAIT(2, 20,"Кликбейт", List.of(
    MetricAndCoefficientDelta.of(Metric.RELEVANCE, 0.9),
    MetricAndCoefficientDelta.of(Metric.VIEW_TIME, 1.5)
  )),

  INVOLVEMENT(2, 35,"Вовлеченность", List.of(
    MetricAndCoefficientDelta.of(Metric.LIKES, 1.3)
  )),

  FRIENDS_AND_POPULARITY(1, 20,"Друзья и популярность", List.of(
    MetricAndCoefficientDelta.of(Metric.CLICKS, 1.1)
  )),

  STRONG_PERSONAL_PREFERENCES(3, 30,"Сильные личные предпочтения", List.of(
    MetricAndCoefficientDelta.of(Metric.AUTHORS_SATISFACTION, 1.2)
  )),

  NOVELTY_OF_CONTENT(2, 15,"Учет новизны контента", List.of(
    MetricAndCoefficientDelta.of(Metric.LIKES, 1.1),
    MetricAndCoefficientDelta.of(Metric.VIEW_TIME, 0.8)
  )),

  SOCIAL_SIGNIFICANCE(3, 25,"Социальная значимость", List.of(
    MetricAndCoefficientDelta.of(Metric.AUTHORS_SATISFACTION, 1.2)
  )),

  REGIONAL_PREFERENCES(4, 40,"Региональные предпочтения", List.of(
    MetricAndCoefficientDelta.of(Metric.RELEVANCE, 1.5),
    MetricAndCoefficientDelta.of(Metric.CLICKS, 0.8)
  )),

  LOCAL_NEWS(3, 35,"Локальные новости", List.of(
    MetricAndCoefficientDelta.of(Metric.LIKES, 1.3),
    MetricAndCoefficientDelta.of(Metric.CLICKS, 1.1)
  )),

  AESTHETIC_PREFERENCES(2, 30,"Эстетические предпочтения", List.of(
    MetricAndCoefficientDelta.of(Metric.LIKES, 0.9),
    MetricAndCoefficientDelta.of(Metric.VIEW_TIME, 1.2)
  ))
  ;

  private final String title;

  private final List<MetricAndCoefficientDelta> metricAndCoefficientDelta;

  FeatureOptions(int memory, int time, String title, List<MetricAndCoefficientDelta> metricAndCoefficientDelta) {
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
