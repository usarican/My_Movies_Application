package com.utkusarican.moviesapplication.features.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import com.utkusarican.moviesapplication.features.home.domain.usecase.FetchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchMovieUseCase: FetchMovieUseCase
) : ViewModel() {

    fun getNowPlayingMovies(language : String) : Flow<PagingData<Movie>> =
        fetchMovieUseCase.fetchNowPlayingMoviesUseCase(language)
            .cachedIn(viewModelScope)

    fun getTopRatedMovies(language : String) : Flow<PagingData<Movie>> =
        fetchMovieUseCase.fetchTopRatedMoviesUseCase(language)
            .cachedIn(viewModelScope)

    fun getPopularMovies(language : String) : Flow<PagingData<Movie>> =
        fetchMovieUseCase.fetchPopularMoviesUseCase(language)
            .cachedIn(viewModelScope)

}