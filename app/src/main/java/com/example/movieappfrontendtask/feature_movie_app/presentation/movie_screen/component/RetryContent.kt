@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.movieappfrontendtask.feature_movie_app.presentation.movie_screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RetryContent(
    error: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {

        Card(
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2700FD)),
            onClick = onRetry
        ) {

            Text(
                text = error,
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.padding(
                    10.dp
                )
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        CircularProgressIndicator(
            color = Color(0xFF2700FD),
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(bottom = 5.dp)
        )
    }

}
