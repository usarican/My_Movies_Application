package com.utkusarican.moviesapplication.features.details.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utkusarican.moviesapplication.features.details.data.model.MovieCredits
import com.utkusarican.moviesapplication.features.details.data.model.MovieDetails
import com.utkusarican.moviesapplication.features.details.domain.model.MovieCast
import com.utkusarican.moviesapplication.features.details.domain.usecase.DetailsFetchUseCase
import com.utkusarican.moviesapplication.features.details.domain.usecase.FetchMovieDetailsUseCase
import com.utkusarican.moviesapplication.utils.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCase: DetailsFetchUseCase
) : ViewModel() {

    private val movieDetailLiveData = MutableLiveData<MovieDetails>()
    private val movieCreditsLiveData = MutableLiveData<List<MovieCast>>()

    fun getMovieDetailLiveData() : LiveData<MovieDetails> = movieDetailLiveData
    fun getMovieCreditsLiveData() : LiveData<List<MovieCast>> = movieCreditsLiveData


    fun getMovieDetails(language : String , movieId : Int) {
        viewModelScope.launch {
            detailsUseCase.fetchMovieDetailsUseCase(language, movieId)
                .doOnSuccess { movieDetails ->
                    movieDetailLiveData.value = movieDetails
                }
                .collect { state ->
                    Log.d("GetMovieDetailsState",state.toString())
                }
        }
    }

    fun getMovieCredits(language: String, movieId: Int) {
        viewModelScope.launch {
            detailsUseCase.fetchMovieCreditsUseCase(language, movieId)
                .doOnSuccess { movieCasts ->
                    movieCreditsLiveData.value = movieCasts
                }
                .collect { state ->
                    Log.d("GetMovieCreditsState",state.toString())
                }
        }
    }

}