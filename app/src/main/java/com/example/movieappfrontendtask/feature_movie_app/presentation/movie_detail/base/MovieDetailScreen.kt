package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_detail.base

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieappfrontendtask.R
import com.example.movieappfrontendtask.feature_movie_app.presentation.common.component.SeeMoreText
import com.example.movieappfrontendtask.feature_movie_app.presentation.common.mapping.toHourMinutes
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_detail.MovieDetailViewModel
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_detail.component.RoundImageDetail
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_detail.component.SubtitlePrimary
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_detail.component.SubtitleSecondary
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.component.ImageHolder
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun MovieDetailScreen(
    movieId: Int,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state

    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {

        state.movies?.let { movieDetail ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                    ImageHolder(
                        contentScale = ContentScale.FillBounds,
                        isLoading = state.isLoading,
                        imageUrl = movieDetail.posterPath,
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .fillMaxWidth()
                            .height(360.dp)
                    )


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        Text(
                            text = movieDetail.title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .padding(all = 15.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp, top = 10.dp)
                                .horizontalScroll(scrollState)
                        ) {

                            Column(Modifier.weight(1f)) {

                                SubtitlePrimary(
                                    text = movieDetail.originalLanguage,
                                    textAlign = TextAlign.Center
                                )
                                SubtitleSecondary(
                                    text = stringResource(R.string.language),
                                    textAlign = TextAlign.Center
                                )

                            }
                            Column(Modifier.weight(1f)) {
                                SubtitlePrimary(
                                    text = movieDetail.voteAverage.toString(),
                                    textAlign = TextAlign.Center
                                )
                                SubtitleSecondary(
                                    text = stringResource(R.string.rate),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                SubtitlePrimary(
                                    text = movieDetail.duration.toHourMinutes(),
                                    textAlign = TextAlign.Center
                                )
                                SubtitleSecondary(
                                    text = stringResource(R.string.duration),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                SubtitlePrimary(
                                    text = movieDetail.releaseDate,
                                    textAlign = TextAlign.Center
                                )
                                SubtitleSecondary(
                                    text = stringResource(R.string.release_date),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        Text(
                            text = stringResource(R.string.description),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .padding(all = 10.dp)
                        )

                        SeeMoreText(text = movieDetail.overview)

                        RoundImageDetail(state = state)

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

            }

        }

        if (state.error?.isNotBlank() == true) {
            Text(
                text = state.error,
                color = Color(0xFFDBE8E1),
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