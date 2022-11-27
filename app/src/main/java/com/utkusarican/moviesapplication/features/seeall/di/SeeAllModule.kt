package com.utkusarican.moviesapplication.features.seeall.di

import com.utkusarican.moviesapplication.features.seeall.data.remote.SeeAllRemoteDataSource
import com.utkusarican.moviesapplication.features.seeall.data.remote.SeeAllService
import com.utkusarican.moviesapplication.features.seeall.data.repository.SeeAllRepositoryImp
import com.utkusarican.moviesapplication.features.seeall.domain.repository.SeeAllRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SeeAllModule {

    @Singleton
    @Provides
    fun provideSeeAllService(retrofit : Retrofit) : SeeAllService =
        retrofit.create(SeeAllService::class.java)

    @Provides
    fun provideSeeAllRepository(seeAllRemoteDataSource: SeeAllRemoteDataSource) : SeeAllRepository =
        SeeAllRepositoryImp(seeAllRemoteDataSource)
}