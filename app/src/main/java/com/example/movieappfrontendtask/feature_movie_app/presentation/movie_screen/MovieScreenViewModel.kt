package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappfrontendtask.core.constant.Resource
import com.example.movieappfrontendtask.feature_movie_app.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    var state by mutableStateOf(MovieScreenState())

    init {
        getMovieResult(category = "now_playing")
    }


    private fun getMovieResult(category: String) {

        viewModelScope.launch {

            movieRepository.getMovie(category)

                .collect { results ->

                    when (results) {

                        is Resource.Success -> {
                            results.data?.let { result ->
                                state = state.copy(movies = result)
                            }
                        }

                        is Resource.Error ->
                            state = state.copy(
                                error = results.message ?: "An unexpected error occurred"
                            )

                        is Resource.Loading -> state = state.copy(isLoading = results.isLoading)

                    }
                }
        }
    }

}