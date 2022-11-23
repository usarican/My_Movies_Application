package com.utkusarican.moviesapplication.utils

object HandleUtils {

    fun handleImageUrl(
        imageUrl: String?
    ) = "$IMAGE_BASE_URL$IMAGE_BASE_SIZE/$imageUrl"
}