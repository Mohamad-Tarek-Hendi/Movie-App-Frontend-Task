package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_detail.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieappfrontendtask.R
import com.example.movieappfrontendtask.feature_movie_app.presentation.movie_detail.MovieDetailState

@Composable
fun RoundImageDetail(
    state: MovieDetailState,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
    ) {

        Text(
            text = "Similar",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(start = 10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(modifier = Modifier.fillMaxHeight()) {

            state.similarMovie?.results?.let {

                items(it.size) {

                    val similar = state.similarMovie.results[it]

                    Column(
                        modifier = Modifier
                            .padding(
                                start = 0.dp, end = 8.dp, top = 5.dp, bottom = 5.dp
                            )
                            .fillMaxWidth()
                    ) {
                        AsyncImage(
                            model = ImageRequest
                                .Builder(LocalContext.current)
                                .data("https://image.tmdb.org/t/p/w500/${similar.posterPath}")
                                .crossfade(true)
                                .build(),
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = modifier
                                .height(150.dp)
                                .width(100.dp)
                                .aspectRatio(1f, matchHeightConstraintsFirst = true)
                                .border(
                                    width = 1.dp,
                                    color = Color.LightGray,
                                    shape = CircleShape
                                )
                                .clip(CircleShape),
                            placeholder = painterResource(R.drawable.baseline_circle_24),
                            error = painterResource(R.drawable.baseline_circle_24)
                        )
                    }
                }
            }
        }
    }
}
