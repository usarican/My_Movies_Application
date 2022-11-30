package com.utkusarican.moviesapplication.features.details.domain.usecase

import com.utkusarican.moviesapplication.core.data.State
import com.utkusarican.moviesapplication.features.details.data.model.MovieCredits
import com.utkusarican.moviesapplication.features.details.domain.model.MovieCast
import com.utkusarican.moviesapplication.features.details.domain.repository.DetailRepository
import com.utkusarican.moviesapplication.utils.stateOfMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchMovieCreditsUseCase @Inject constructor(
    private val movieDetailRepository: DetailRepository
) {

    suspend operator fun invoke(language : String, movieId : Int) : Flow<State<List<MovieCast>>> =
        movieDetailRepository.fetchMovieCredits(language, movieId).map { state ->
            state.stateOfMap { movieCredits ->
                movieCredits.cast
            }
        }
}