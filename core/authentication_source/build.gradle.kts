plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.core.authentication_api"
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    api(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:data_store"))
    implementation(project(":core:model"))

    implementation(Dependencies.coroutines_core)

    //hilt
    implementation(Dependencies.hilt_android)
    kapt(Dependencies.hilt_compiler)

    //retrofit
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit_converter_gson)
}