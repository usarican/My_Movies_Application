package com.utkusarican.moviesapplication.features.seeall.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface SeeAllRepository {

    fun fetchPopularMovies(language : String) : Flow<PagingData<Movie>>
    fun fetchTopRatedMovies(language : String) : Flow<PagingData<Movie>>
    fun searchMovies(language: String,query : String) : LiveData<PagingData<Movie>>
}