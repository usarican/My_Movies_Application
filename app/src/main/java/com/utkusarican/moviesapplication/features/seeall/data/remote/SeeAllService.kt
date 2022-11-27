package com.utkusarican.moviesapplication.features.seeall.data.remote

import com.utkusarican.moviesapplication.features.home.data.model.ApiResponse
import com.utkusarican.moviesapplication.utils.API_KEY
import com.utkusarican.moviesapplication.utils.STARTING_PAGE
import retrofit2.http.GET
import retrofit2.http.Query

interface SeeAllService {

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(
        @Query("api_key") apiKey : String = API_KEY,
        @Query("page") page : Int = STARTING_PAGE,
        @Query("language") language: String
    ) : ApiResponse

    @GET("movie/popular")
    suspend fun fetchPopularMovies(
        @Query("api_key") apiKey : String = API_KEY,
        @Query("page") page : Int = STARTING_PAGE,
        @Query("language") language: String
    ) : ApiResponse

}