package com.example.movieappfrontendtask.feature_movie_app.domain.model.movie

data class MovieResult(
    val isAdult: Boolean,
    val categoriesList: List<Int>,
    val movieId: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)