package com.utkusarican.moviesapplication.features.details.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.utkusarican.moviesapplication.features.details.domain.usecase.FetchMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val detailsUseCase: FetchMovieDetailsUseCase
) : ViewModel() {
}