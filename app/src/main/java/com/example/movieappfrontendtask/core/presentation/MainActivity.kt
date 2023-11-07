package com.example.movieappfrontendtask.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.movieappfrontendtask.core.presentation.ui.theme.MovieAppFrontendTaskTheme
import com.example.movieappfrontendtask.feature_movie_app.presentation.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppFrontendTaskTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}