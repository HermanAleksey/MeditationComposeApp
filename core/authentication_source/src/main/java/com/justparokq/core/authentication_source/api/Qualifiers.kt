package com.justparokq.core.authentication_source.api

import javax.inject.Qualifier

class Qualifiers {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AuthRetrofit
}