package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie.MovieResult

@Composable
fun MovieItem(
    isLoading: Boolean,
    moviesResult: MovieResult,
    modifier: Modifier = Modifier,
    onCardClick: (MovieResult) -> Unit
) {
    Card(
        modifier = modifier
            .clickable {
                onCardClick(moviesResult)
            }
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFDBE8E1))
    ) {
        Box(modifier = Modifier.height(200.dp)) {

            ImageHolder(
                contentScale = ContentScale.FillWidth,
                imageUrl = moviesResult.posterPath,
                modifier = Modifier.fillMaxSize(),
                isLoading = isLoading
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 200f
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize(), contentAlignment = Alignment.BottomStart
            ) {
                Column() {

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = moviesResult.title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(5.dp),
                        color = Color(0xFFDBE8E1)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = moviesResult.originalLanguage ?: "",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(5.dp),
                            color = Color(0xFFDBE8E1)
                        )
                        Text(
                            text = moviesResult.releaseDate,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(5.dp),
                            color = Color(0xFFDBE8E1)
                        )
                    }
                }
            }
        }
    }
}