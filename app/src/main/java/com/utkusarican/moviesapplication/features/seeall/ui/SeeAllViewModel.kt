package com.utkusarican.moviesapplication.features.seeall.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import com.utkusarican.moviesapplication.features.seeall.domain.usecase.SeeAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    private val seeAllUseCase: SeeAllUseCase
) : ViewModel() {

    private val searchQuery = MutableLiveData(DEFAULT_QUERY)

    fun changeSearchQuery(query: String) {
        searchQuery.value = query
    }

    fun searchMovie(language: String): LiveData<PagingData<Movie>> {
        return searchQuery.switchMap { query ->
            seeAllUseCase.searchMoviesUseCase(language, query).cachedIn(viewModelScope)
        }
    }

    fun getTopRatedMovies(language : String) : Flow<PagingData<Movie>> =
        seeAllUseCase.fetchTopRatedMoviesUseCase(language)
            .cachedIn(viewModelScope)

    fun getPopularMovies(language : String): Flow<PagingData<Movie>> =
        seeAllUseCase.fetchPopularMoviesUseCase(language)
            .cachedIn(viewModelScope)


    companion object {
        private const val DEFAULT_QUERY = "Black"
    }
}