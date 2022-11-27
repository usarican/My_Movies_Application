package com.utkusarican.moviesapplication.features.seeall.domain.usecase

import androidx.paging.PagingData
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import com.utkusarican.moviesapplication.features.home.domain.repository.HomeRepository
import com.utkusarican.moviesapplication.features.seeall.domain.repository.SeeAllRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchTopRatedMoviesUseCase @Inject constructor(
    private val seeAllRepository: SeeAllRepository
) {
    operator fun invoke(language : String) : Flow<PagingData<Movie>> =
        seeAllRepository.fetchTopRatedMovies(language)
}