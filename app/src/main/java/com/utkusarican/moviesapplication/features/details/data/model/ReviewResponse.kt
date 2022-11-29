package com.utkusarican.moviesapplication.features.details.data.model

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    val id : Int,
    val page : Int,
    val results : List<Author>,
    val total_pages : Int,
    val total_results : Int
) {
    data class Author(
        val author : String,
        val author_details : AuthorDetails,
        val content : String
    ) {
        data class AuthorDetails(
            @SerializedName("name") val authorName : String,
            val username : String,
            @SerializedName("avatar_path") val authorImage : String
        )
    }
}
