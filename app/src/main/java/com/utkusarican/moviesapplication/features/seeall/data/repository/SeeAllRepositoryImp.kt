package com.utkusarican.moviesapplication.features.seeall.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.utkusarican.moviesapplication.features.home.data.model.enum.MoviesType
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import com.utkusarican.moviesapplication.features.seeall.data.paging_source.SeeAllPagingSource
import com.utkusarican.moviesapplication.features.seeall.data.remote.SeeAllRemoteDataSource
import com.utkusarican.moviesapplication.features.seeall.domain.repository.SeeAllRepository
import com.utkusarican.moviesapplication.utils.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeeAllRepositoryImp @Inject constructor(
    private val seeAllRemoteDataSource: SeeAllRemoteDataSource,
) : SeeAllRepository {

    override fun fetchPopularMovies(language: String): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE
            ),
            pagingSourceFactory = {
                SeeAllPagingSource(
                    seeAllRemoteDataSource = seeAllRemoteDataSource,
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
                SeeAllPagingSource(
                    seeAllRemoteDataSource = seeAllRemoteDataSource,
                    language = language,
                    moviesType = MoviesType.TOP_RATED
                )
            }
        ).flow


}