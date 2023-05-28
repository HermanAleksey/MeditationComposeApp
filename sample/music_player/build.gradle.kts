plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-android")//?
    id("kotlin-kapt")

    id("org.jlleitschuh.gradle.ktlint")
}


@Suppress("UnstableApiUsage")
android {
    namespace = "com.example.sample.media_player"
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        applicationId = "com.example.sample.media_player"
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionName = Config.VERSION_NAME
        versionCode = Config.VERSION_CODE

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    kapt {
        correctErrorTypes = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = LibVersions.compose_compiler_version
    }
}

dependencies {
    implementation(project(":core:design_system"))
    implementation(project(":core:common"))

    implementation(project(":feature:music_player"))

    implementation(Dependencies.compose_ui)
    implementation(Dependencies.compose_material)

    // Dagger - Hilt
    implementation(Dependencies.hilt_android)
    implementation(Dependencies.hilt_navigation_compose)
    kapt(Dependencies.hilt_compiler)

}