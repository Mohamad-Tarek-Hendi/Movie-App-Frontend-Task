package com.example.movieappfrontendtask.feature_movie_app.data.mapper.movie_mapping

import com.example.movieappfrontendtask.feature_movie_app.data.remote.dto.movie_dto.MovieDto
import com.example.movieappfrontendtask.feature_movie_app.data.remote.dto.movie_dto.MovieResultDto
import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie.Movie
import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie.MovieResult

fun MovieDto.toMovie(): Movie {
    return Movie(
        page = page,
        results = results.map {
            it.toMovieResult()
        },
        totalPagesNumber = totalPagesNumber,
        totalResultNumber = totalResultNumber
    )
}

fun MovieResultDto.toMovieResult(): MovieResult {
    return MovieResult(
        isAdult = isAdult,
        categoriesList = categoriesList,
        movieId = movieId,
        overview = overview,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}
