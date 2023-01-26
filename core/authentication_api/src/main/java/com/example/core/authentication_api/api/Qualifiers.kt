package com.example.core.authentication_api.api

import javax.inject.Qualifier

class Qualifiers {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AuthRetrofit
}