package com.utkusarican.moviesapplication.features.details.data.paging_soruce

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.utkusarican.moviesapplication.features.details.data.model.SimilarMovieResponse
import com.utkusarican.moviesapplication.features.details.data.remote.MovieDetailsRemoteDataSource
import com.utkusarican.moviesapplication.utils.STARTING_PAGE
import javax.inject.Inject

class SimilarMoviePagingSource @Inject constructor(
    private val movieDetailsRemoteDataSource: MovieDetailsRemoteDataSource,
    private val language : String,
    private val movieId : Int
) : PagingSource<Int,SimilarMovieResponse.SimilarMovie>(){

    override fun getRefreshKey(state: PagingState<Int, SimilarMovieResponse.SimilarMovie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimilarMovieResponse.SimilarMovie> {
        val nextPage = params.key ?: STARTING_PAGE
        return try {
            val response = movieDetailsRemoteDataSource.fetchSimilarMovies(
                page = nextPage,
                language = language,
                movieId = movieId
            )
            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if(nextPage <= response.total_pages) nextPage + 1 else null
            )
        }catch (e : Exception){
            LoadResult.Error(e)
        }
    }
}