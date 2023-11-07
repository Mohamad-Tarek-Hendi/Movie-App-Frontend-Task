package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_detail.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieappfrontendtask.R
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_detail.MovieDetailViewModel
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_detail.component.SubtitlePrimary
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_detail.component.SubtitleSecondary
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun MovieDetailScreen(
    movieId: Int,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Box(modifier = Modifier.fillMaxSize()) {

        state.movies?.let { movieDetail ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                    AsyncImage(
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data("https://image.tmdb.org/t/p/w500/${movieDetail.posterPath}")
                            .crossfade(true)
                            .build(),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .fillMaxWidth()
                            .aspectRatio(16 / 9f),
                        placeholder = painterResource(R.drawable.baseline_downloading_24),
                        error = painterResource(R.drawable.baseline_downloading_24)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp)
                    ) {
                        Text(
                            text = movieDetail.title,
                            modifier = Modifier.padding(top = 10.dp),
                            color = Color.Black,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.W700,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp, top = 10.dp)
                        ) {

                            Column(Modifier.weight(1f)) {

                                SubtitlePrimary(
                                    text = movieDetail.originalLanguage,
                                )
                                SubtitleSecondary(
                                    text = stringResource(R.string.language)
                                )

                            }
                            Column(Modifier.weight(1f)) {
                                SubtitlePrimary(
                                    text = movieDetail.voteAverage.toString(),
                                )
                                SubtitleSecondary(
                                    text = stringResource(R.string.rate)
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                SubtitlePrimary(
                                    text = "1h 28m"
                                )
                                SubtitleSecondary(
                                    text = stringResource(R.string.duration)
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                SubtitlePrimary(
                                    text = movieDetail.releaseDate
                                )
                                SubtitleSecondary(
                                    text = stringResource(R.string.release_date)
                                )
                            }
                        }
                        Text(
                            text = stringResource(R.string.description),
                            color = Color.Black,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = movieDetail.overview,
                            color = Color.Black,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                    }
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