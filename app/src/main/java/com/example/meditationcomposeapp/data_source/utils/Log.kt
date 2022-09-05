package com.example.meditationcomposeapp.data_source.utils

import android.util.Log

fun Class<*>.printEventLog(string: String) {
    Log.d("TAG_D", "${javaClass.canonicalName}: $string")
}