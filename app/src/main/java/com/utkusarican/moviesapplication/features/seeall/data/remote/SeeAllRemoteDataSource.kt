package com.utkusarican.moviesapplication.features.seeall.data.remote

import com.utkusarican.moviesapplication.features.home.data.model.ApiResponse
import javax.inject.Inject

class SeeAllRemoteDataSource @Inject constructor(
    private val seeAllService: SeeAllService
) {
    suspend fun fetchPopularMovies(
        page : Int,
        language : String
    ) : ApiResponse =
        seeAllService.fetchPopularMovies(page = page, language = language)

    suspend fun fetchTopRatedMovies(
        page : Int,
        language : String
    ) : ApiResponse =
        seeAllService.fetchTopRatedMovies(page = page, language = language)

    suspend fun searchMovies(
        page: Int,
        query: String,
        language: String
    ) : ApiResponse =
        seeAllService.searchMovies(page = page, query = query, language = language)
}