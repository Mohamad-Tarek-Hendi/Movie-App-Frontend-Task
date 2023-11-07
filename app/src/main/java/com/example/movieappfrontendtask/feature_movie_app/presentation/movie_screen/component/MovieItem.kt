package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieappfrontendtask.R
import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie.MovieResult

@Composable
fun MovieItem(
    moviesResult: MovieResult,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500/${moviesResult.posterPath}")
                    .crossfade(true)
                    .build(),
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .clip(RoundedCornerShape(4.dp))
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f),
                placeholder = painterResource(R.drawable.baseline_downloading_24),
                error = painterResource(R.drawable.baseline_downloading_24)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = moviesResult.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = moviesResult.originalLanguage ?: "",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = moviesResult.releaseDate,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

    }
}