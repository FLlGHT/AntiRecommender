package com.flight.antirecommender.data;

import com.flight.antirecommender.entity.Metric;
import com.flight.antirecommender.entity.MetricAndSumDelta;
import com.flight.antirecommender.entity.WithId;
import com.flight.antirecommender.entity.WithTitle;

import java.util.List;

/**
 * @author FLIGHT
 */
public enum CandidateOptions implements WithTitle, WithId {

  WATCHED_AUTHORS(2, 30, "Авторы, которых я смотрел", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 15),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, -5)
  )),

  CATS(1, 20, "Контент с котиками", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, -5),
    MetricAndSumDelta.of(Metric.LIKES, 10)
  )),

  POPULAR(3, 35, "Популярный контент", List.of(
    MetricAndSumDelta.of(Metric.LIKES, -10),
    MetricAndSumDelta.of(Metric.CLICKS, 20)
  )),

  NEWS(2, 25, "Новостной контент", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 10),
    MetricAndSumDelta.of(Metric.VIEW_TIME, 5)
  )),

  WATCH_HISTORY(3, 40, "История просмотров", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 10),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 10)
  )),

  PETS_CONTENT(4, 50, "Контент с животными", List.of(
    MetricAndSumDelta.of(Metric.LIKES, 5),
    MetricAndSumDelta.of(Metric.VIEW_TIME, 5)
  )),

  MUSIC_CLIPS(2, 60, "Музыкальные клипы", List.of(
    MetricAndSumDelta.of(Metric.VIEW_TIME, 20),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, -15)
  )),

  BOOKS_CONTENT(1, 30, "Контент с книгами", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 5),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 5)
  )),

  SPORT(3, 55, "Спорт", List.of(
    MetricAndSumDelta.of(Metric.VIEW_TIME, -5),
    MetricAndSumDelta.of(Metric.CLICKS, 15)
  )),

  FRIENDS_INTERESTS(1, 45, "Контент по интересам друзей", List.of(
    MetricAndSumDelta.of(Metric.LIKES, 10),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 10)
  )),

  SCIENCE_CONTENT(5, 65, "Научный контент", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 20),
    MetricAndSumDelta.of(Metric.LIKES, -10)
  )),

  SHOWS(4, 50, "Развлекательные шоу", List.of(
    MetricAndSumDelta.of(Metric.LIKES, 5),
    MetricAndSumDelta.of(Metric.VIEW_TIME, 15)
  )),

  GAME_VIDEOS(2, 40, "Игровые видео", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 5),
    MetricAndSumDelta.of(Metric.CLICKS, 10)
  )),

  REVIEW_VIDEOS(2, 35, "Обзорные видео", List.of(
    MetricAndSumDelta.of(Metric.VIEW_TIME, 5),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 5)
  )),

  TRAVELLING(3, 45, "Контент с путешествиями", List.of(
    MetricAndSumDelta.of(Metric.LIKES, -5),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 10)
  )),

  COMEDY(3, 30, "Комедийные видео", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, -10),
    MetricAndSumDelta.of(Metric.LIKES, 20)
  )),

  DOCUMENTARIES(3, 55, "Документальные фильмы", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 15),
    MetricAndSumDelta.of(Metric.CLICKS, -5)
  )),

  LESSONS(4, 65, "Уроки и семинары", List.of(
    MetricAndSumDelta.of(Metric.VIEW_TIME, 10),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 10)
  )),

  PROFESSIONAL(2, 50, "Профессиональный контент", List.of(
    MetricAndSumDelta.of(Metric.LIKES, 5),
    MetricAndSumDelta.of(Metric.CLICKS, 5)
  )),

  THEMATIC(1, 25, "Тематическое видео", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 10),
    MetricAndSumDelta.of(Metric.VIEW_TIME, -10)
  )),

  GRADE_BASED(2, 35, "Рекомендации на основе оценок", List.of(
    MetricAndSumDelta.of(Metric.LIKES, 5),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 10)
  )),

  CATEGORIES(3, 45, "Контент по категориям", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 10),
    MetricAndSumDelta.of(Metric.VIEW_TIME, 5)
  )),

  TRENDS(3, 55, "Актуальные тренды", List.of(
    MetricAndSumDelta.of(Metric.CLICKS, 15),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, -5)
  )),

  CREATIVE(2, 50, "Креативный контент", List.of(
    MetricAndSumDelta.of(Metric.LIKES, 5),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 5)
  )),

  ARCHIVE(2, 45, "Архивы старых видео", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 15),
    MetricAndSumDelta.of(Metric.CLICKS, 5)
  )),

  PATTERN_SEARCH(1, 30, "Поиск по шаблонам", List.of(
    MetricAndSumDelta.of(Metric.LIKES, -5),
    MetricAndSumDelta.of(Metric.VIEW_TIME, 10)
  )),

  HISTORIC(3, 40, "Исторический контент", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 15),
    MetricAndSumDelta.of(Metric.LIKES, -10)
  )),

  CREATIVE_NEW(2, 35, "Новинки креативов", List.of(
    MetricAndSumDelta.of(Metric.LIKES, 10),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 5)
  )),

  EVENTS_REVIEW(3, 55, "Обзор событий", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 10),
    MetricAndSumDelta.of(Metric.VIEW_TIME, 5)
  )),

  TECHNO(4, 50, "Технологический контент", List.of(
    MetricAndSumDelta.of(Metric.RELEVANCE, 15),
    MetricAndSumDelta.of(Metric.AUTHORS_SATISFACTION, 5)
  ));;

  private final String title;

  private final List<MetricAndSumDelta> metricAndSumDelta;

  CandidateOptions(int memory, int time, String title, List<MetricAndSumDelta> metricAndSumDelta) {
    this.title = title;
    this.metricAndSumDelta = metricAndSumDelta;
  }

  public static final CandidateOptions[] activeCandidateSelectors =
    {
      WATCHED_AUTHORS
      , CATS
      , POPULAR
      , NEWS
      , WATCH_HISTORY
      , PETS_CONTENT
      , MUSIC_CLIPS
      , BOOKS_CONTENT
      , SPORT
      , FRIENDS_INTERESTS
      , SCIENCE_CONTENT
      , SHOWS
      , GAME_VIDEOS
      , REVIEW_VIDEOS
      , TRAVELLING
      , COMEDY
      , DOCUMENTARIES
      , LESSONS
      , PROFESSIONAL
      , THEMATIC
      , GRADE_BASED
      , CATEGORIES
      , TRENDS
      , CREATIVE
      , ARCHIVE
      , PATTERN_SEARCH
      , HISTORIC
//      , CREATIVE_NEW
      , EVENTS_REVIEW
      , TECHNO
    };

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
