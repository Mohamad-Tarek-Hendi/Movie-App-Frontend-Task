package com.example.movieappfrontendtask.feature_movie_app.data.remote.dto.movie_dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val page: Int,
    val results: List<MovieResultDto>,
    @SerializedName("total_pages")
    val totalPagesNumber: Int,
    @SerializedName("total_results")
    val totalResultNumber: Int
)
