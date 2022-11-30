package com.utkusarican.moviesapplication.features.details.domain.usecase

import androidx.paging.PagingData
import com.utkusarican.moviesapplication.features.details.data.model.SimilarMovieResponse
import com.utkusarican.moviesapplication.features.details.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import org.intellij.lang.annotations.Language
import javax.inject.Inject

class FetchSimilarMoviesUseCase @Inject constructor(
    private val detailRepository: DetailRepository
) {

    operator fun invoke(language : String, movieId : Int) : Flow<PagingData<SimilarMovieResponse.SimilarMovie>> =
        detailRepository.fetchSimilarMovies(language, movieId)
}