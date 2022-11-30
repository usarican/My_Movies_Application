package com.utkusarican.moviesapplication.features.details.domain.repository

import androidx.paging.PagingData
import com.utkusarican.moviesapplication.core.data.State
import com.utkusarican.moviesapplication.features.details.data.model.*
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    suspend fun fetchMovieDetail(language : String, movieId : Int) : Flow<State<MovieDetails>>
    suspend fun fetchMovieCredits(language: String, movieId: Int) : Flow<State<MovieCredits>>
    suspend fun fetchMovieVideos(language: String, movieId: Int) : Flow<State<MovieVideoResponse>>
    fun fetchSimilarMovies(language: String, movieId: Int) : Flow<PagingData<SimilarMovieResponse.SimilarMovie>>
    fun fetchMovieReview(language: String,movieId: Int) : Flow<PagingData<ReviewResponse.Author>>
}