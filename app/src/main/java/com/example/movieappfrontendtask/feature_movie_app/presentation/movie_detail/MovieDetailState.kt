package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_detail

import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie_detail.MovieDetail

data class MovieDetailState(
    val movies: MovieDetail? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)