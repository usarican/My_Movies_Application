package com.utkusarican.moviesapplication.features.details.domain.usecase

import com.utkusarican.moviesapplication.core.data.State
import com.utkusarican.moviesapplication.features.details.data.model.MovieDetails
import com.utkusarican.moviesapplication.features.details.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMovieDetailsUseCase @Inject constructor(
    private val detailRepository: DetailRepository
) {
    suspend operator fun invoke(language : String, movieId : Int) : Flow<State<MovieDetails>> =
        detailRepository.fetchMovieDetail(language,movieId)
}