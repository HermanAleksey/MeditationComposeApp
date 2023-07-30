package com.example.common.feature_toggle

interface FeatureToggle {

    fun getKey(): String = this.javaClass.canonicalName ?: ""

    fun getDefaultValue(): Boolean

    fun getName(): String

    fun getDescription(): String
}