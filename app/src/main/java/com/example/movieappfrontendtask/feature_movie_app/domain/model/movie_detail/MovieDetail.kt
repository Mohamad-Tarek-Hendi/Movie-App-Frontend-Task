package com.example.movieappfrontendtask.feature_movie_app.domain.model.movie_detail

import com.example.movieapitask.feature_movie_app.domain.model.movie.SpokenLanguage

data class MovieDetail(
    val isAdult: Boolean,
    val categories: List<Category>,
    val id: Int,
    val originalLanguage: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)