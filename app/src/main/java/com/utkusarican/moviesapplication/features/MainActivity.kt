package com.utkusarican.moviesapplication.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.utkusarican.moviesapplication.R
import com.utkusarican.moviesapplication.utils.API_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MAIN_ACTIVITY", API_KEY)
    }
}