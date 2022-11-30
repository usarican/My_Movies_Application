package com.utkusarican.moviesapplication.features.details.data.remote

import com.utkusarican.moviesapplication.features.details.data.model.*
import com.utkusarican.moviesapplication.utils.API_KEY
import com.utkusarican.moviesapplication.utils.STARTING_PAGE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") apiKey : String = API_KEY,
        @Query("language") language: String,
    ) : MovieDetails

    @GET("movie/{movie_id}/credits")
    suspend fun fetchMovieCredits(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") apiKey : String = API_KEY,
        @Query("language") language: String,
    ) : MovieCredits

    @GET("movie/{movie_id}/similar")
    suspend fun fetchSimilarMovies(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") apiKey : String = API_KEY,
        @Query("page") page : Int = STARTING_PAGE,
        @Query("language") language: String
    ) : SimilarMovieResponse

    @GET("movie/{movie_id}/videos")
    suspend fun fetchMovieVideos(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") apiKey : String = API_KEY,
        @Query("language") language: String
    ) : MovieVideoResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun fetchMovieReviews(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") apiKey : String = API_KEY,
        @Query("page") page : Int = STARTING_PAGE,
        @Query("language") language: String,
    ) : ReviewResponse
}