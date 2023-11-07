package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappfrontendtask.core.constant.Resource
import com.example.movieappfrontendtask.feature_movie_app.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(MovieDetailState())

    init {
        val movieId = savedStateHandle.get<Int>("movieId") ?: 1
        getMovieDetailResult(
            movieId = movieId,
        )
    }

    private fun getMovieDetailResult(movieId: Int) {

        viewModelScope.launch {

            movieRepository.getMovieDetail(movieId = movieId)

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