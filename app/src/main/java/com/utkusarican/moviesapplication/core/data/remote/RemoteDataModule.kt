package com.utkusarican.moviesapplication.core.data.remote

import com.utkusarican.moviesapplication.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        movieAppUrl : String,
        gsonConverterFactory: GsonConverterFactory,
        httpClient: OkHttpClient
    ) : Retrofit =
        Retrofit.Builder()
            .baseUrl(movieAppUrl)
            .client(httpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Singleton
    @Provides
    fun provideMovieAppUrl() : String = BASE_URL

    @Singleton
    @Provides
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    @Singleton
    @Provides
    fun provideGsonConverterFactory() : GsonConverterFactory =
        GsonConverterFactory.create()
}