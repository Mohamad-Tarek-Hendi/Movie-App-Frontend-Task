package com.example.movieappfrontendtask.feature_movie_app.data.remote.dto.similar_movie

import com.google.gson.annotations.SerializedName

data class SimilarMovieDto(
    val page: Int,
    val results: List<SimilarMovieResultDto>,
    @SerializedName("total_pages")
    val totalPagesNumber: Int,
    @SerializedName("total_results")
    val totalResultNumber: Int
)