package com.example.punk_source.api

import javax.inject.Qualifier

class Qualifiers {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PunkRetrofit
}