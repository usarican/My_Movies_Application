package com.utkusarican.moviesapplication.features.home.data.model

import com.google.gson.annotations.SerializedName
import com.utkusarican.moviesapplication.features.home.domain.model.Movie

data class ApiResponse(
    val page : Int,
    @SerializedName("results") val movies : List<Movie>,
    val total_pages : Int,
    val total_results: Int
)
