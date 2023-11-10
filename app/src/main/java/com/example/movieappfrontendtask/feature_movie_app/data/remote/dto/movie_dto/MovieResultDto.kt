package com.example.movieappfrontendtask.feature_movie_app.data.remote.dto.movie_dto

import com.google.gson.annotations.SerializedName

data class MovieResultDto(
    @SerializedName("adult")
    val isAdult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("genre_ids")
    val categoriesList: List<Int> = emptyList(),
    @SerializedName("id")
    val movieId: Int = 0,
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0
)