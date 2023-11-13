package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_detail

import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie_detail.MovieDetail
import com.example.movieappfrontendtask.feature_movie_app.domain.model.similar_movie.SimilarMovie

data class MovieDetailState(
    val movies: MovieDetail? = null,
    val similarMovie: SimilarMovie? = null,
    val isLoading: Boolean = false,
    val isLoadingShimmer: Boolean = true,
    val error: String? = null,

    )