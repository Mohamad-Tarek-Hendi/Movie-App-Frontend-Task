package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ImageHolder(
    isLoading: Boolean,
    imageUrl: String?,
    modifier: Modifier
) {

    ShimmerListItem(
        isLoading = isLoading,
        contentAfterLoading = {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500/${imageUrl}")
                    .crossfade(true)
                    .build(),
                contentDescription = "Image",
                contentScale = ContentScale.FillWidth,
                modifier = modifier,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )

}