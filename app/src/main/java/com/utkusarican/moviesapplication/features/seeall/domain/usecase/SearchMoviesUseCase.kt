package com.utkusarican.moviesapplication.features.seeall.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import com.utkusarican.moviesapplication.features.seeall.domain.repository.SeeAllRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val seeAllRepository: SeeAllRepository
) {
    operator fun invoke(language: String, query: String): LiveData<PagingData<Movie>> =
        seeAllRepository.searchMovies(language, query)
}