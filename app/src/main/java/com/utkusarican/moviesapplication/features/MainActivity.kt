package com.utkusarican.moviesapplication.features

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.view.get
import com.google.android.material.navigation.NavigationBarView
import com.utkusarican.moviesapplication.R
import com.utkusarican.moviesapplication.utils.API_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttomNavigationBarSetup()
        fullScreen()

    }

    private fun buttomNavigationBarSetup(){
        val buttomNavBar = findViewById<NavigationBarView>(R.id.bottomNavigationBar)
        buttomNavBar.background = null
        buttomNavBar.menu.getItem(2).isEnabled = false
    }

    private fun fullScreen(){
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }
}