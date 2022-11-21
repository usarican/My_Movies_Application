package com.utkusarican.moviesapplication.features.home.domain.usecase

import androidx.paging.PagingData
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import com.utkusarican.moviesapplication.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchNowPlayingMoviesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    operator fun invoke(language : String) : Flow<PagingData<Movie>> {
        return homeRepository.fetchNowPlayingMovies(language = language)
    }
}