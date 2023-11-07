package com.example.movieappfrontendtask.core.di

import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.movieappfrontendtask.feature_movie_app.data.remote.MovieApi
import com.example.movieappfrontendtask.feature_movie_app.data.remote.MovieApi.Companion.BASE_URL
import com.example.movieappfrontendtask.feature_movie_app.data.repository.MovieRepositoryImpl
import com.example.movieappfrontendtask.feature_movie_app.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApi: MovieApi
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieApi = movieApi
        )
    }


}
