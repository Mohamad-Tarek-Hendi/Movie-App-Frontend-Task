package com.example.movieappfrontendtask.feature_movie_app.domain.repository

import com.example.movieappfrontendtask.core.constant.Resource
import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie.Movie
import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie_detail.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovie(
        category: String
    ): Flow<Resource<Movie>>

    fun getMovieDetail(
        movieId: Int
    ): Flow<Resource<MovieDetail>>

    fun searchForMovies(
        searchText: String
    ): Flow<Resource<Movie>>

}