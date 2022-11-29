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
        @Query("api_key") apiKey : String = API_KEY,
        @Query("language") language: String,
        @Path("movie_id") movieId : Int
    ) : MovieDetails

    @GET("movie/{movie_id}/credits")
    suspend fun fetchMovieCredits(
        @Query("api_key") apiKey : String = API_KEY,
        @Query("language") language: String,
        @Path("movie_id") movieId : Int
    ) : MovieCredits

    @GET("movie/{movie_id}/similar")
    suspend fun fetchSimilarMovies(
        @Query("api_key") apiKey : String = API_KEY,
        @Query("page") page : Int = STARTING_PAGE,
        @Query("language") language: String,
        @Path("movie_id") movieId : Int
    ) : SimilarMovieResponse

    @GET("movie/{movie_id}/videos")
    suspend fun fetchMovieVideos(
        @Query("api_key") apiKey : String = API_KEY,
        @Query("language") language: String,
        @Path("movie_id") movieId : Int
    ) : MovieVideoResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun fetchMovieReviews(
        @Query("api_key") apiKey : String = API_KEY,
        @Query("page") page : Int = STARTING_PAGE,
        @Query("language") language: String,
        @Path("movie_id") movieId : Int
    ) : ReviewResponse
}