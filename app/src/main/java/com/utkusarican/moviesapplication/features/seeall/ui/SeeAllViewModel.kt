package com.utkusarican.moviesapplication.features.seeall.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import com.utkusarican.moviesapplication.features.seeall.domain.usecase.SeeAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    private val seeAllUseCase: SeeAllUseCase
) : ViewModel() {

    fun getTopRatedMovies(language : String) : Flow<PagingData<Movie>> =
        seeAllUseCase.fetchTopRatedMoviesUseCase(language)
            .cachedIn(viewModelScope)

    fun getPopularMovies(language : String) : Flow<PagingData<Movie>> =
        seeAllUseCase.fetchPopularMoviesUseCase(language)
            .cachedIn(viewModelScope)
}