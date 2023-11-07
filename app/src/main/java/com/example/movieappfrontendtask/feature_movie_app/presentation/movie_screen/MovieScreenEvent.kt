package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen

sealed class MovieScreenEvent {
    object Refresh : MovieScreenEvent()
    data class OnCategoryChanged(var category: String) : MovieScreenEvent()
}
