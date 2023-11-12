package com.example.movieappfrontendtask.feature_movie_app.data.mapper.similar_movie_mapping

import com.example.movieappfrontendtask.feature_movie_app.data.remote.dto.similar_movie.SimilarMovieDto
import com.example.movieappfrontendtask.feature_movie_app.data.remote.dto.similar_movie.SimilarMovieResultDto
import com.example.movieappfrontendtask.feature_movie_app.domain.model.similar_movie.SimilarMovie
import com.example.movieappfrontendtask.feature_movie_app.domain.model.similar_movie.SimilarMovieResult

fun SimilarMovieDto.toSimilarMovie(): SimilarMovie {
    return SimilarMovie(
        page = page,
        results = results.map {
            it.toSimilarMovieResult()
        },
        totalPagesNumber = totalPagesNumber,
        totalResultNumber = totalResultNumber
    )
}

fun SimilarMovieResultDto.toSimilarMovieResult(): SimilarMovieResult {
    return SimilarMovieResult(
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
