package com.utkusarican.moviesapplication.utils

import com.utkusarican.moviesapplication.features.home.domain.model.Genre

object HandleUtils {

    fun handleImageUrl(
        imageUrl: String?
    ) = "$IMAGE_BASE_URL$IMAGE_BASE_SIZE/$imageUrl"

    fun handleRealseDate(releaseDate : String) =
        releaseDate.split("-")[0]

    fun handleGenreText(genresList : List<Genre>,movieGenreList : List<Int>) : String {
        var genreText = ""
        var count = 0
        genresList.forEach { genre ->
            for (genreIds in movieGenreList){
                if (genreIds == genre.id ){
                    genreText += if ((count != (movieGenreList.size - 1))){
                        "${genre.name},"
                    } else {
                        "${genre.name}"
                    }
                    count++
                }
            }
        }
        return genreText
    }

    fun handleGenreOneText(genresList : List<Genre>, movieGenreList: List<Int>) : String {
        for (genre in genresList) {
            for (genreId: Int in movieGenreList) {
                if (genreId == genre.id) {
                    return genre.name
                }
                break

            }
        }
        return ""
    }
}