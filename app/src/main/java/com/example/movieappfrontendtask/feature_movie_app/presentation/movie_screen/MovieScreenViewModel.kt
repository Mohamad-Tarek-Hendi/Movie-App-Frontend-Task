package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappfrontendtask.core.constant.Resource
import com.example.movieappfrontendtask.feature_movie_app.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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


    private var searchJob: Job? = null

    fun onEvent(event: MovieScreenEvent) {

        when (event) {

            is MovieScreenEvent.OnCategoryChange -> {
                state = state.copy(category = event.category)
                when (event.category) {
                    "Now Playing" -> getMovieResult(category = "now_playing")
                    "Popular" -> getMovieResult(category = "popular")
                    "Top Rated" -> getMovieResult(category = "top_rated")
                    "Upcoming" -> getMovieResult(category = "upcoming")
                }
            }

            is MovieScreenEvent.OnMovieCardClick -> {
                state = state.copy(movieResult = event.movie)
            }

            is MovieScreenEvent.ShowSimilarMovie -> {
                state = state.copy(isLoadingShimmerBottom = true)
                getSimilarMovieResult(movieId = event.movieId)
            }

            MovieScreenEvent.OnSearchIconClicked -> {
                state = state.copy(
                    isSearchBarVisible = true,
                    movie = null,
                    movieResult = null
                )
            }

            MovieScreenEvent.OnButtomSheetContentHide -> {
                state = state.copy(
                    movieResult = null,
                    similarMoviesResult = null,
                    similarMovie = null
                )
            }

            MovieScreenEvent.OnCloseSearchIconClick -> {
                state = state.copy(
                    isSearchBarVisible = false,
                    movie = null,
                    movieResult = null
                )
                getMovieResult(category = state.category)
            }

            is MovieScreenEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.searchQuery)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(2000L)
                    searchForNews(searchText = state.searchQuery)
                }

            }

            else -> {}
        }
    }

    private fun getMovieResult(category: String) {

        viewModelScope.launch {

            movieRepository.getMovie(category).collect { movieResults ->

                when (movieResults) {

                    is Resource.Success -> {
                        movieResults.data?.let { result ->
                            state = state.copy(
                                movie = result,
                                isLoading = false,
                                error = null,
                                isLoadingShimmer = false
                            )
                        }
                    }

                    is Resource.Error ->
                        state = state.copy(
                            movie = null,
                            isLoading = false,
                            isLoadingShimmer = false,
                            error = movieResults.message ?: "An unexpected error occurred"
                        )

                    is Resource.Loading -> state = state.copy(
                        isLoading = movieResults.isLoading,
                        searchQuery = "",
                        isSearchBarVisible = false,
                        isLoadingShimmer = movieResults.isLoading
                    )

                }
            }
        }
    }

    private fun getSimilarMovieResult(movieId: Int) {

        viewModelScope.launch {

            movieRepository.getSimilarMovies(movieId = movieId)

                .collect { results ->

                    when (results) {

                        is Resource.Success -> {
                            results.data?.let { result ->
                                state = state.copy(
                                    similarMovie = result,
                                    isLoading = false,
                                    error = null,
                                    isLoadingShimmerBottom = false
                                )
                            }
                        }

                        is Resource.Error ->
                            state = state.copy(
                                error = results.message ?: "An unexpected error occurred",
                                similarMovie = null,
                                isLoading = false,
                                isLoadingShimmerBottom = false,
                            )

                        is Resource.Loading -> state = state.copy(
                            isLoading = results.isLoading,
                        )

                    }
                }
        }
    }

    private fun searchForNews(searchText: String) {

        if (searchText.isEmpty()) {
            return
        }

        viewModelScope.launch {
            movieRepository.searchForMovies(searchText).collect { searchResult ->

                when (searchResult) {

                    is Resource.Success -> {
                        state = state.copy(
                            movie = searchResult.data,
                            isLoading = false,
                            error = null
                        )

                    }

                    is Resource.Error -> {
                        state = state.copy(
                            movie = null,
                            error = searchResult.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }

                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = searchResult.isLoading
                        )
                    }

                }
            }

        }
    }

}