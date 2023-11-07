package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.base

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieappfrontendtask.feature_movie_app.presentation.destinations.MovieDetailScreenDestination
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.MovieScreenViewModel
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.component.MovieItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class,
    ExperimentalFoundationApi::class
)
@Composable
@RootNavGraph(start = true)
@Destination()
fun MovieScreen(
    navigator: DestinationsNavigator,
    viewModel: MovieScreenViewModel = hiltViewModel()
) {

    val state = viewModel.state


    Box(modifier = Modifier.fillMaxSize()) {

        state.movies?.let { movie ->

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(movie.results.size) { i ->

                    val movieResult = movie.results[i]

                    if (i > 0) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    MovieItem(
                        moviesResult = movieResult,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navigator.navigate(
                                    MovieDetailScreenDestination(movieId = movieResult.movieId)
                                )
                            }
                    )
                }

            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}