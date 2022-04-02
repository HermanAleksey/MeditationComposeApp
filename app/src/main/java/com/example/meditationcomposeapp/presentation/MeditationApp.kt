package com.example.meditationcomposeapp.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MeditationApp: Application() {

    override fun onCreate() {
        super.onCreate()

    }
}