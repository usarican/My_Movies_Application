package com.utkusarican.moviesapplication.features.home.domain.usecase

import com.utkusarican.moviesapplication.core.data.State
import com.utkusarican.moviesapplication.features.home.domain.model.Genre
import com.utkusarican.moviesapplication.features.home.domain.repository.HomeRepository
import com.utkusarican.moviesapplication.utils.stateOfMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchGenresUseCase @Inject constructor(
    private val homeRepository: HomeRepository
){
    suspend operator fun invoke(language : String) : Flow<State<List<Genre>>> =
        homeRepository.fetchGenres(language).map { state ->
            state.stateOfMap { genres ->
                genres.genres
            }
        }

}