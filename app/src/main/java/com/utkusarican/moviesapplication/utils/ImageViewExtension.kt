package com.utkusarican.moviesapplication.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.utkusarican.moviesapplication.R

inline fun ImageView.addImage(imageUrl : String,context : Context){
    Glide
        .with(context)
        .load(HandleUtils.handleImageUrl(imageUrl))
        .centerCrop()
        .placeholder(R.drawable.loading_animate)
        .into(this)

}