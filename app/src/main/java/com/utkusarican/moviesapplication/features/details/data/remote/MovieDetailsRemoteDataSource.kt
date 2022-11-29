package com.utkusarican.moviesapplication.features.details.data.remote

import com.utkusarican.moviesapplication.features.details.data.model.*
import javax.inject.Inject

class MovieDetailsRemoteDataSource @Inject constructor(
    private val movieDetailService: MovieDetailService
) {

    suspend fun fetchMovieDetails(language : String, movieId : Int) : MovieDetails =
        movieDetailService.fetchMovieDetails(language = language, movieId = movieId)

    suspend fun fetchMovieCredits(language : String, movieId : Int) : MovieCredits =
        movieDetailService.fetchMovieCredits(language = language, movieId = movieId)

    suspend fun fetchSimilarMovies(language: String,page : Int, movieId: Int) : SimilarMovieResponse =
        movieDetailService.fetchSimilarMovies(language = language, page = page, movieId = movieId)

    suspend fun fetchMovieVideos(language: String, movieId: Int) : MovieVideoResponse =
        movieDetailService.fetchMovieVideos(language = language, movieId = movieId)

    suspend fun fetchMovieReviews(language: String, page: Int, movieId: Int) : ReviewResponse =
        movieDetailService.fetchMovieReviews(language = language, page = page, movieId = movieId)

}