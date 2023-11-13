package com.example.movieappfrontendtask.feature_movie_app.presentation.common.mapping

import kotlin.time.Duration.Companion.minutes

fun Int.toHourMinutes(): String {
    return "${this.minutes.inWholeHours}h ${this % 60}m"
}