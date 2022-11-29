package com.utkusarican.moviesapplication.features.details.data.model

import com.google.gson.annotations.SerializedName

data class SimilarMovieResponse(
    val page: Int,
    val results : SimilarMovie
) {
    data class SimilarMovie(
        val id : Int,
        @SerializedName("poster_path") val image : String,
        @SerializedName("vote_average") val score : Double,
    )
}
