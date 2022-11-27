package com.utkusarican.moviesapplication.features.seeall.domain.usecase

import javax.inject.Inject

data class SeeAllUseCase @Inject constructor(
    val fetchPopularMoviesUseCase: FetchPopularMoviesUseCase,
    val fetchTopRatedMoviesUseCase: FetchTopRatedMoviesUseCase
    ) {
}