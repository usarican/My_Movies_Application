package com.utkusarican.moviesapplication.utils

inline fun Double.oneDecimal() : Double =
    String.format("%.1f",this).toDouble()