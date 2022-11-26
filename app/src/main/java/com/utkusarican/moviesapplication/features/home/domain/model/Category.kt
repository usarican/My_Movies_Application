package com.utkusarican.moviesapplication.features.home.domain.model

import androidx.annotation.StringRes
import androidx.paging.PagingData

data class Category(
    @StringRes val categoryName : Int,
    val movies : PagingData<Movie>
)
