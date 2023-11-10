package com.example.movieappfrontendtask.feature_movie_app.domain.model.movie

data class Movie(
    val page: Int? = null,
    val results: List<MovieResult> = emptyList(),
    val totalPagesNumber: Int? = null,
    val totalResultNumber: Int? = null
)
