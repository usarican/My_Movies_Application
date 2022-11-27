package com.utkusarican.moviesapplication.features.home.domain.repository

import androidx.paging.PagingData
import com.utkusarican.moviesapplication.core.data.State
import com.utkusarican.moviesapplication.features.home.data.model.Genres
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun fetchPopularMovies(language : String) : Flow<PagingData<Movie>>
    fun fetchTopRatedMovies(language : String) : Flow<PagingData<Movie>>
    fun fetchNowPlayingMovies(language : String) : Flow<PagingData<Movie>>
    suspend fun fetchGenres(language: String) : Flow<State<Genres>>

}