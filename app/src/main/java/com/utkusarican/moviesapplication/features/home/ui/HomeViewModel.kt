package com.utkusarican.moviesapplication.features.home.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.utkusarican.moviesapplication.features.home.domain.model.Genre
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import com.utkusarican.moviesapplication.features.home.domain.usecase.FetchMovieUseCase
import com.utkusarican.moviesapplication.utils.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchMovieUseCase: FetchMovieUseCase
) : ViewModel() {

    private val genresLiveData = MutableLiveData<List<Genre>>()
    fun getGenresLiveData() : LiveData<List<Genre>> = genresLiveData

    fun getNowPlayingMovies(language : String) : Flow<PagingData<Movie>> =
        fetchMovieUseCase.fetchNowPlayingMoviesUseCase(language)
            .cachedIn(viewModelScope)

    fun getTopRatedMovies(language : String) : Flow<PagingData<Movie>> =
        fetchMovieUseCase.fetchTopRatedMoviesUseCase(language)
            .cachedIn(viewModelScope)

    fun getPopularMovies(language : String) : Flow<PagingData<Movie>> =
        fetchMovieUseCase.fetchPopularMoviesUseCase(language)
            .cachedIn(viewModelScope)

    fun getGenreList(language: String) {
        viewModelScope.launch {
            fetchMovieUseCase.fetchGenresUseCase(language)
                .doOnSuccess { genres ->
                    Log.d("HomeViewModel",genres.toString())
                    genresLiveData.value = genres
                }.collect { state ->
                    Log.d("HomeViewModel",state.toString())
                }
        }

    }

}