package com.utkusarican.moviesapplication.features.home.ui

import androidx.lifecycle.ViewModel
import com.utkusarican.moviesapplication.features.home.domain.usecase.FetchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchMovieUseCase: FetchMovieUseCase
) : ViewModel() {


}