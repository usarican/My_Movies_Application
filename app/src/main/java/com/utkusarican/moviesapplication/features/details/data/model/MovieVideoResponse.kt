package com.utkusarican.moviesapplication.features.details.data.model

import com.google.gson.annotations.SerializedName

data class MovieVideoResponse(
  val id : Int,
  val results : List<MovieVideo>
) {
  data class MovieVideo(
    @SerializedName("iso_639_1") val iso639_1: String,
    @SerializedName("iso_3166_1")
    val iso3166_1: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Long,
    val type: String,
    val official: Boolean,
    @SerializedName("published_at")
    val publishedAt: String,
    val id: String
  )
}