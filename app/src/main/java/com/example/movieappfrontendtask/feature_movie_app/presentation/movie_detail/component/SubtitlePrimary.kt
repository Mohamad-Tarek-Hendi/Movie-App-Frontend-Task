package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_detail.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SubtitlePrimary(text: String, textAlign: TextAlign) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        textAlign = textAlign,
        modifier = Modifier.fillMaxWidth()
    )
}
