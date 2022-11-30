package com.utkusarican.moviesapplication.features.details.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.utkusarican.moviesapplication.features.details.domain.usecase.DetailsFetchUseCase
import com.utkusarican.moviesapplication.features.details.domain.usecase.FetchMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrailersViewModel @Inject constructor(
    private val detailsUseCase: DetailsFetchUseCase
) : ViewModel() {
}