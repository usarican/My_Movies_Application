package com.utkusarican.moviesapplication.features.details.domain.usecase

import javax.inject.Inject

data class DetailsFetchUseCase @Inject constructor(
    val fetchMovieCreditsUseCase: FetchMovieCreditsUseCase,
    val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase,
    val fetchMovieReviewsUseCase: FetchMovieReviewsUseCase,
    val fetchMovieVideosUseCase: FetchMovieVideosUseCase,
    val fetchSimilarMoviesUseCase: FetchSimilarMoviesUseCase
)