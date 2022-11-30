package com.utkusarican.moviesapplication.features.details.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.utkusarican.moviesapplication.core.data.BaseRepository
import com.utkusarican.moviesapplication.core.data.State
import com.utkusarican.moviesapplication.features.details.data.model.*
import com.utkusarican.moviesapplication.features.details.data.paging_soruce.MovieReviewPagingSource
import com.utkusarican.moviesapplication.features.details.data.paging_soruce.SimilarMoviePagingSource
import com.utkusarican.moviesapplication.features.details.data.remote.MovieDetailsRemoteDataSource
import com.utkusarican.moviesapplication.features.details.domain.repository.DetailRepository
import com.utkusarican.moviesapplication.utils.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailsRepositoryImp @Inject constructor(
    private val movieDetailsRemoteDataSource: MovieDetailsRemoteDataSource
) : DetailRepository,BaseRepository(){

    override suspend fun fetchMovieDetail(
        language: String,
        movieId: Int
    ): Flow<State<MovieDetails>> =
        apiCall { movieDetailsRemoteDataSource.fetchMovieDetails(language,movieId) }

    override suspend fun fetchMovieCredits(
        language: String,
        movieId: Int
    ): Flow<State<MovieCredits>> {
        return apiCall { movieDetailsRemoteDataSource.fetchMovieCredits(language, movieId) }
    }

    override suspend fun fetchMovieVideos(
        language: String,
        movieId: Int
    ): Flow<State<MovieVideoResponse>> {
        return apiCall { movieDetailsRemoteDataSource.fetchMovieVideos(language, movieId) }
    }

    override fun fetchSimilarMovies(
        language: String,
        movieId: Int
    ): Flow<PagingData<SimilarMovieResponse.SimilarMovie>> {
        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE
            ),
            pagingSourceFactory = {
                SimilarMoviePagingSource(
                    movieDetailsRemoteDataSource = movieDetailsRemoteDataSource,
                    language = language,
                    movieId = movieId
                )
            }
        ).flow
    }

    override fun fetchMovieReview(
        language: String,
        movieId: Int
    ): Flow<PagingData<ReviewResponse.Author>> {
        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE
            ),
            pagingSourceFactory = {
                MovieReviewPagingSource(
                    movieDetailsRemoteDataSource = movieDetailsRemoteDataSource,
                    language = language,
                    movieId = movieId
                )
            }
        ).flow
    }
}