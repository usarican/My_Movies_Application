package com.utkusarican.moviesapplication.features.details.data.model

import com.google.gson.annotations.SerializedName


data class MovieDetails(
    val genres : List<Genre>,
    val title : String,
    @SerializedName("poster_path") val image : String,
    @SerializedName("release_date") val date : String,
    val overview : String,
    @SerializedName("vote_average") val score : Double,
){
    data class Genre(
        val id : Int,
        val name : String
    )
}
