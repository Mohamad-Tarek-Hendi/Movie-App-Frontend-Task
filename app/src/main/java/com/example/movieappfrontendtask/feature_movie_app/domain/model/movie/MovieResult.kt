package com.example.movieappfrontendtask.feature_movie_app.domain.model.movie

data class MovieResult(
    val isAdult: Boolean = false,
    val categoriesList: List<Int> = emptyList(),
    val movieId: Int = 0,
    val overview: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val popularity: Double = 0.0,
    val posterPath: String? = "",
    val releaseDate: String = "",
    val title: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)