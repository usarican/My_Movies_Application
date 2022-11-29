package com.utkusarican.moviesapplication.features.seeall.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import com.utkusarican.moviesapplication.features.seeall.data.remote.SeeAllRemoteDataSource
import com.utkusarican.moviesapplication.utils.STARTING_PAGE
import java.io.IOException
import javax.inject.Inject

class SeeAllSearchPagingSource @Inject constructor(
    private val seeAllRemoteDataSource: SeeAllRemoteDataSource,
    private val language : String,
    private val query : String
) : PagingSource<Int, Movie>(){

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val nextPage = params.key ?: STARTING_PAGE
        return try {
            val response = seeAllRemoteDataSource.searchMovies(
                page = nextPage,
                query = query,
                language = language
            )
            LoadResult.Page(
                data = response.movies,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nextPage < response.total_pages)
                    response.page.plus(1) else null
            )
        }catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}