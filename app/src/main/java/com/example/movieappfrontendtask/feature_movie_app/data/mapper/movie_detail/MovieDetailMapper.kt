package com.example.movieappfrontendtask.feature_movie_app.data.mapper.movie_detail

import com.example.movieapitask.feature_movie_app.data.remote.dto.movie_dto.CategoryDto
import com.example.movieapitask.feature_movie_app.data.remote.dto.movie_dto.MovieDetailDto
import com.example.movieapitask.feature_movie_app.data.remote.dto.movie_dto.SpokenLanguageDto
import com.example.movieapitask.feature_movie_app.domain.model.movie.SpokenLanguage
import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie_detail.Category
import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie_detail.MovieDetail

fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        isAdult = isAdult,
        categories = category.map {
            it.toCategory()
        },
        id = id,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        revenue = revenue,
        spokenLanguages = spokenLanguages.map {
            it.toSpokenLanguage()
        },
        status = status,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}

fun CategoryDto.toCategory(): Category {
    return Category(
        id = id,
        name = name
    )
}

fun SpokenLanguageDto.toSpokenLanguage(): SpokenLanguage {
    return SpokenLanguage(
        englishName = english_name,
        name = name
    )
}

