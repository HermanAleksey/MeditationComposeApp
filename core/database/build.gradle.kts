plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.justparokq.core.database"
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK

        buildConfigField("String", "DATABASE_NAME", "\"MeditationComposeApp\"")
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
    //room database
    implementation(Dependencies.room_runtime)
    implementation(Dependencies.room_ktx)
    kapt(Dependencies.room_compiler)
    implementation(Dependencies.room_pager)

    //hilt
    implementation(Dependencies.hilt_android)
    kapt(Dependencies.hilt_compiler)
}