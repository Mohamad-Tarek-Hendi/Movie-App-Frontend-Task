package com.example.movieapitask.feature_movie_app.presentation.movie_screen

sealed class MovieDetailEvent {
    object Refresh : MovieDetailEvent()
}
