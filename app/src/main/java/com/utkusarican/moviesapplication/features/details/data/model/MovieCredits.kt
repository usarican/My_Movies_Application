package com.utkusarican.moviesapplication.features.details.data.model

import com.google.gson.annotations.SerializedName

data class MovieCredits(
    val id : Int,
    val cast : List<MovieCast>
) {
    data class MovieCast(
        val name : String,
        @SerializedName("character") val characterName : String,
        @SerializedName("profile_path") val profileImage : String
    )
}