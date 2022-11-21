package com.utkusarican.moviesapplication.features.home.data.remote

import com.utkusarican.moviesapplication.features.home.data.model.ApiResponse
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(
    private val homeMovieService: HomeMovieService
) {

    suspend fun fetchPopularMovies(
        page : Int,
        language : String
    ) : ApiResponse =
        homeMovieService.fetchPopularMovies(page = page, language = language)

    suspend fun fetchTopRatedMovies(
        page : Int,
        language : String
    ) : ApiResponse =
        homeMovieService.fetchTopRatedMovies(page = page, language = language)

    suspend fun fetchNowPlayingMovies(
        page : Int,
        language : String
    ) : ApiResponse =
        homeMovieService.fetchNowPlayingMovies(page = page, language = language)
}