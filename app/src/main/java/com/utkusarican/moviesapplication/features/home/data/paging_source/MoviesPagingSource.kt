package com.utkusarican.moviesapplication.features.home.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.utkusarican.moviesapplication.features.home.data.model.enum.MoviesType
import com.utkusarican.moviesapplication.features.home.data.remote.HomeRemoteDataSource
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import com.utkusarican.moviesapplication.utils.STARTING_PAGE
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource,
    private val language : String,
    private val moviesType: MoviesType
) : PagingSource<Int,Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val nextPage = params.key ?: STARTING_PAGE

        return try {
            val response =
                when(moviesType){
                    MoviesType.NOW_PLAYING -> {
                        remoteDataSource.fetchNowPlayingMovies(nextPage,language)
                    }
                    MoviesType.POPULAR -> {
                        remoteDataSource.fetchPopularMovies(nextPage,language)
                    }
                    MoviesType.TOP_RATED -> {
                        remoteDataSource.fetchTopRatedMovies(nextPage,language)
                    }
                }

            LoadResult.Page(
                data = response.movies,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nextPage < response.total_pages)
                    response.page.plus(1) else null
            )
        }catch (e : Exception){
            LoadResult.Error(e)
        }
    }

}