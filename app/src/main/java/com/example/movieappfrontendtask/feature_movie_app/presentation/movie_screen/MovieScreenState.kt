package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen

import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie.Movie
import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie.MovieResult

data class MovieScreenState(
    val movie: Movie? = null,
    val movieResult: MovieResult? = null,
    val isLoading: Boolean = false,
    val isSearchBarVisible: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val category: String = "now_playing"
)