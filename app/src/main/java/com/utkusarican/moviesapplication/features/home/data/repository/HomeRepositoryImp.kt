package com.utkusarican.moviesapplication.features.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.utkusarican.moviesapplication.core.data.BaseRepository
import com.utkusarican.moviesapplication.features.home.data.model.enum.MoviesType
import com.utkusarican.moviesapplication.features.home.data.paging_source.MoviesPagingSource
import com.utkusarican.moviesapplication.features.home.data.remote.HomeRemoteDataSource
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import com.utkusarican.moviesapplication.features.home.domain.repository.HomeRepository
import com.utkusarican.moviesapplication.utils.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource
) : HomeRepository, BaseRepository(){


    override fun fetchPopularMovies(language: String): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE
            ),
            pagingSourceFactory = {
                MoviesPagingSource(
                    remoteDataSource = homeRemoteDataSource,
                    language = language,
                    moviesType = MoviesType.POPULAR
                )
            }
        ).flow

    override fun fetchTopRatedMovies(language: String): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE
            ),
            pagingSourceFactory = {
                MoviesPagingSource(
                    remoteDataSource = homeRemoteDataSource,
                    language = language,
                    moviesType = MoviesType.TOP_RATED
                )
            }
        ).flow

    override fun fetchNowPlayingMovies(language: String): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE
            ),
            pagingSourceFactory = {
                MoviesPagingSource(
                    remoteDataSource = homeRemoteDataSource,
                    language = language,
                    moviesType = MoviesType.NOW_PLAYING
                )
            }
        ).flow
}