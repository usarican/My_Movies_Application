package com.utkusarican.moviesapplication.features.details.domain.usecase

import androidx.paging.PagingData
import com.utkusarican.moviesapplication.features.details.data.model.ReviewResponse
import com.utkusarican.moviesapplication.features.details.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMovieReviewsUseCase @Inject constructor(
    private val detailRepository: DetailRepository
) {
    operator fun invoke(language: String, movieId : Int) : Flow<PagingData<ReviewResponse.Author>> =
        detailRepository.fetchMovieReview(language,movieId)
}