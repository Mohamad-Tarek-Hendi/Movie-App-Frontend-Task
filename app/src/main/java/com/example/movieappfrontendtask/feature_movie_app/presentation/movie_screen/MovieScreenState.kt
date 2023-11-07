package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen

import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie.Movie

data class MovieScreenState(
    val movies: Movie? = null,
    val isLoading: Boolean = false,
    val isSearchBarVisible: Boolean = false,
    val error: String = ""
)