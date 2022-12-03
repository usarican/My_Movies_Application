package com.utkusarican.moviesapplication.features.details.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.utkusarican.moviesapplication.features.details.data.model.SimilarMovieResponse
import com.utkusarican.moviesapplication.features.details.domain.usecase.DetailsFetchUseCase
import com.utkusarican.moviesapplication.features.details.domain.usecase.FetchMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreLikeThisViewModel @Inject constructor(
    private val detailsUseCase: DetailsFetchUseCase
) : ViewModel(){

    fun getSimilarMovies(lanugage : String, movieId : Int) =
        detailsUseCase.fetchSimilarMoviesUseCase(lanugage,movieId).cachedIn(viewModelScope)
}