package com.example.movieappfrontendtask.feature_movie_app.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.movieappfrontendtask.core.constant.Resource
import com.example.movieappfrontendtask.feature_movie_app.data.mapper.Movie_mapping.toMovie
import com.example.movieappfrontendtask.feature_movie_app.data.mapper.movie_detail.toMovieDetail
import com.example.movieappfrontendtask.feature_movie_app.data.remote.MovieApi
import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie.Movie
import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie_detail.MovieDetail
import com.example.movieappfrontendtask.feature_movie_app.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class MovieRepositoryImpl(
    private val movieApi: MovieApi,
) : MovieRepository {

    override fun getMovie(
        category: String
    ): Flow<Resource<Movie>> = flow {

        emit(Resource.Loading(true))

        val remoteMovies = try {
            movieApi.getMoviesByCategory(category = category)
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't Reach Server, Check Your Internet Connection."))
            null
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't Load Data"))
            null
        }

        remoteMovies?.let { movie ->
            emit(Resource.Success(data = movie.toMovie()))
        }

        emit(Resource.Loading(false))

    }

    override fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>> = flow {

        emit(Resource.Loading(true))

        val remoteMovieDetail = try {
            movieApi.getMovieDetail(movieId = movieId)
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't Reach Server, Check Your Internet Connection."))
            null
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't Load Data"))
            null
        }

        remoteMovieDetail?.let { movieDetail ->
            emit(Resource.Success(data = movieDetail.toMovieDetail()))
        }

        emit(Resource.Loading(false))

    }
}