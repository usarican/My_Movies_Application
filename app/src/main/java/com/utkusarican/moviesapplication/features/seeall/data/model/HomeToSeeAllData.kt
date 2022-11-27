package com.utkusarican.moviesapplication.features.seeall.data.model

import android.os.Parcelable
import com.utkusarican.moviesapplication.features.home.data.model.enum.MoviesType
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeToSeeAllData(
  val title : String,
  val moviesType: MoviesType
) : Parcelable