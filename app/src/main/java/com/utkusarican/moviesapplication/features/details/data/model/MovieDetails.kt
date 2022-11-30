package com.utkusarican.moviesapplication.features.details.data.model

import com.google.gson.annotations.SerializedName
import com.utkusarican.moviesapplication.features.details.domain.model.DetailGenre


data class MovieDetails(
    @SerializedName("genres") val detailGenres : List<DetailGenre>,
    val title : String,
    @SerializedName("poster_path") val image : String,
    @SerializedName("release_date") val date : String,
    val overview : String,
    @SerializedName("vote_average") val score : Double,
)

