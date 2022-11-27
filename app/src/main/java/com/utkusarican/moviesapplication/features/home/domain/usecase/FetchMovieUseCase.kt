package com.utkusarican.moviesapplication.features.home.domain.usecase

import javax.inject.Inject

data class FetchMovieUseCase @Inject constructor(
    val fetchNowPlayingMoviesUseCase: FetchNowPlayingMoviesUseCase,
    val fetchPopularMoviesUseCase: FetchPopularMoviesUseCase,
    val fetchTopRatedMoviesUseCase: FetchTopRatedMoviesUseCase,
    val fetchGenresUseCase: FetchGenresUseCase
)