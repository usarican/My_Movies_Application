package com.utkusarican.moviesapplication.features.details.domain.model

import com.google.gson.annotations.SerializedName

data class MovieCast(
    val name : String,
    @SerializedName("character") val characterName : String,
    @SerializedName("profile_path") val profileImage : String
)
