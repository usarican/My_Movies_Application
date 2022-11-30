package com.utkusarican.moviesapplication.features.details.data.model

import com.utkusarican.moviesapplication.features.details.domain.model.MovieCast

data class MovieCredits(
    val id : Int,
    val cast : List<MovieCast>
) {
}