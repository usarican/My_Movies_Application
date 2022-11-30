package com.utkusarican.moviesapplication.features.details.data.model

import com.google.gson.annotations.SerializedName

data class SimilarMovieResponse(
    val page: Int,
    val results : List<SimilarMovie>,
    val total_pages : Int,
    val total_results : Int
) {
    data class SimilarMovie(
        val id : Int,
        @SerializedName("poster_path") val image : String,
        @SerializedName("vote_average") val score : Double,
    )
}
