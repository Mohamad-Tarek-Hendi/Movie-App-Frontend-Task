package com.example.movieappfrontendtask.feature_movie_app.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.movieappfrontendtask.core.constant.Resource
import com.example.movieappfrontendtask.feature_movie_app.data.mapper.movie_detail_mapping.toMovieDetail
import com.example.movieappfrontendtask.feature_movie_app.data.mapper.movie_mapping.toMovie
import com.example.movieappfrontendtask.feature_movie_app.data.mapper.similar_movie_mapping.toSimilarMovie
import com.example.movieappfrontendtask.feature_movie_app.data.remote.MovieApi
import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie.Movie
import com.example.movieappfrontendtask.feature_movie_app.domain.model.movie_detail.MovieDetail
import com.example.movieappfrontendtask.feature_movie_app.domain.model.similar_movie.SimilarMovie
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

        val remoteMoviesResult = try {
            movieApi.getMoviesByCategory(category = category)
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(
                Resource.Error(
                    "Couldn't Reach Server, Check Your Internet Connection.",
                )
            )
            null
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't Load Data"))
            null
        }

        remoteMoviesResult?.let { movieResult ->
            emit(Resource.Success(data = movieResult.toMovie()))
        }

        emit(Resource.Loading(false))

    }

    override fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>> = flow {

        emit(Resource.Loading(true))

        val remoteMovieDetailResult = try {
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

        remoteMovieDetailResult?.let { movieDetailResult ->
            emit(Resource.Success(data = movieDetailResult.toMovieDetail()))
        }

        emit(Resource.Loading(false))

    }

    override fun getSimilarMovies(movieId: Int): Flow<Resource<SimilarMovie>> = flow {

        emit(Resource.Loading(true))

        val remoteSimilarMoviesResult = try {
            movieApi.getSimilarMovies(movieId = movieId)
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't Reach Server, Check Your Internet Connection."))
            null
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't Load Data"))
            null
        }

        remoteSimilarMoviesResult?.let { similarMovieResult ->
            emit(Resource.Success(data = similarMovieResult.toSimilarMovie()))
        }

        emit(Resource.Loading(false))

    }

    override fun searchForMovies(searchText: String): Flow<Resource<Movie>> = flow {

        emit(Resource.Loading(true))

        val remoteSearchingResult = try {
            movieApi.searchForMovies(searchText = searchText)
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't Reach Server, Check Your Internet Connection."))
            null
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't Load Data"))
            null
        }

        remoteSearchingResult?.let { searchResult ->
            emit(Resource.Success(data = searchResult.toMovie()))
        }

        emit(Resource.Loading(false))
    }
}