package com.utkusarican.moviesapplication.features.details.di

import com.utkusarican.moviesapplication.features.details.data.remote.MovieDetailService
import com.utkusarican.moviesapplication.features.details.data.remote.MovieDetailsRemoteDataSource
import com.utkusarican.moviesapplication.features.details.data.repository.DetailsRepositoryImp
import com.utkusarican.moviesapplication.features.details.domain.repository.DetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailsModule {

    @Singleton
    @Provides
    fun provideMovieDetailService(retrofit: Retrofit) : MovieDetailService =
        retrofit.create(MovieDetailService::class.java)

    @Provides
    fun provideDetailsRepository(movieDetailsRemoteDataSource: MovieDetailsRemoteDataSource) : DetailRepository =
        DetailsRepositoryImp(movieDetailsRemoteDataSource = movieDetailsRemoteDataSource)
}