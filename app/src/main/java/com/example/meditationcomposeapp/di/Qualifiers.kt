package com.example.meditationcomposeapp.di

import javax.inject.Qualifier

class Qualifiers {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RandomDataRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AuthRetrofit
}