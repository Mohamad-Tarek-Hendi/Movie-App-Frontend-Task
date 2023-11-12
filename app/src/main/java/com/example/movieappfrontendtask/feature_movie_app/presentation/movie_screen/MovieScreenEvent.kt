package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen

import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie.MovieResult


sealed class MovieScreenEvent {
    data class OnMovieCardClick(var movie: MovieResult) : MovieScreenEvent()
    data class OnCategoryChange(var category: String) : MovieScreenEvent()
    data class OnSearchQueryChange(var searchQuery: String) : MovieScreenEvent()
    object Refresh : MovieScreenEvent()
    object OnCloseSearchIconClick : MovieScreenEvent()
    object OnSearchIconClicked : MovieScreenEvent()
    object OnButtomSheetContentHide : MovieScreenEvent()
    data class ShowSimilarMovie(var movieId: Int) : MovieScreenEvent()
}
