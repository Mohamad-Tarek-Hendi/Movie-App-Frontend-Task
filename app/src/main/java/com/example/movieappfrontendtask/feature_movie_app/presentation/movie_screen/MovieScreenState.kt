package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen

import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie.Movie
import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie.MovieResult
import com.example.movieappfrontendtask.feature_movie_app.domain.model.similar_movie.SimilarMovie
import com.example.movieappfrontendtask.feature_movie_app.domain.model.similar_movie.SimilarMovieResult

data class MovieScreenState(
    val movie: Movie? = null,
    val movieResult: MovieResult? = null,
    val similarMovie: SimilarMovie? = null,
    val similarMoviesResult: SimilarMovieResult? = null,
    val isLoading: Boolean = false,
    val isLoadingShimmer: Boolean = false,
    val isLoadingShimmerBottom: Boolean = true,
    val isSearchBarVisible: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val category: String = "now_playing"
)