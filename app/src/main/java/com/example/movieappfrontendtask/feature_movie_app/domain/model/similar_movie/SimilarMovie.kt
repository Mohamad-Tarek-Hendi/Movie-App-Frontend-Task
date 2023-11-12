package com.example.movieappfrontendtask.feature_movie_app.domain.model.similar_movie

data class SimilarMovie(
    val page: Int? = null,
    val results: List<SimilarMovieResult> = emptyList(),
    val totalPagesNumber: Int? = null,
    val totalResultNumber: Int? = null
)
