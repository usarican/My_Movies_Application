package com.utkusarican.moviesapplication.features.home.domain.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id : Int,
    val genre_ids : List<Int>,
    @SerializedName("title") val name : String,
    @SerializedName("poster_path") val image : String,
    @SerializedName("vote_average") val score : Double
)
