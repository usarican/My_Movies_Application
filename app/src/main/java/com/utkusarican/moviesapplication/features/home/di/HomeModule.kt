package com.utkusarican.moviesapplication.features.home.di

import com.utkusarican.moviesapplication.features.home.data.remote.HomeMovieService
import com.utkusarican.moviesapplication.features.home.data.remote.HomeRemoteDataSource
import com.utkusarican.moviesapplication.features.home.data.repository.HomeRepositoryImp
import com.utkusarican.moviesapplication.features.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun provideHomeMovieService(retrofit: Retrofit) : HomeMovieService =
        retrofit.create(HomeMovieService::class.java)

    @Provides
    fun provideHomeRepository(homeRemoteDataSource: HomeRemoteDataSource) : HomeRepository =
        HomeRepositoryImp(homeRemoteDataSource = homeRemoteDataSource)
}