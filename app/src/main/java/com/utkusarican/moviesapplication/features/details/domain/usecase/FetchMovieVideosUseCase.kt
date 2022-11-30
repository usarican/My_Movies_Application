package com.utkusarican.moviesapplication.features.details.domain.usecase

import com.utkusarican.moviesapplication.core.data.State
import com.utkusarican.moviesapplication.features.details.data.model.MovieVideoResponse
import com.utkusarican.moviesapplication.features.details.domain.repository.DetailRepository
import com.utkusarican.moviesapplication.utils.stateOfMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchMovieVideosUseCase @Inject constructor(
    private val detailRepository: DetailRepository
) {
    suspend operator fun invoke(language: String, movieId : Int) : Flow<State<List<MovieVideoResponse.MovieVideo>>> =
        detailRepository.fetchMovieVideos(language,movieId).map { state ->
            state.stateOfMap { movieVideoResponse ->
                movieVideoResponse.results
            }
        }
}