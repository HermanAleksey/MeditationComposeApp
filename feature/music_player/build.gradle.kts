plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp") version LibVersions.ksp_version // Depends on your kotlin version
}

android {
    namespace = "com.example.feature.music_player"
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

    @Suppress("UnstableApiUsage")
    buildFeatures {
        compose = true
    }
    @Suppress("UnstableApiUsage")
    composeOptions {
        kotlinCompilerExtensionVersion = LibVersions.compose_compiler_version
    }
}

dependencies {
    implementation(project(":core:design_system"))
    implementation(project(":core:common"))

    implementation("androidx.core:core-ktx:1.7.0-alpha01")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.compose.ui:ui:1.0.0-rc01")
    implementation("androidx.compose.material:material:${LibVersions.compose_ui_version}")
    implementation("androidx.compose.ui:ui-tooling:${LibVersions.compose_ui_version}")
    implementation("androidx.activity:activity-compose:1.3.0-rc01")
    implementation("com.google.firebase:firebase-firestore-ktx:23.0.2")
    implementation("com.google.accompanist:accompanist-insets:0.13.0")
    implementation("com.google.accompanist:accompanist-pager:0.13.0")
    implementation("androidx.compose.material:material:1.0.0-rc01")
    implementation("androidx.compose.foundation:foundation:1.0.0-rc01")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha08")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-rc01")
    implementation("androidx.palette:palette-ktx:1.0.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-rc01")
    implementation("androidx.compose.runtime:runtime:1.0.0-rc01")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    // Coroutines
    implementation(Dependencies.coroutines_core)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${LibVersions.coroutines_version}")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")

    // Activity KTX for viewModels()
    implementation("androidx.activity:activity-ktx:1.3.0-rc01")

    // Coil
    implementation("io.coil-kt:coil:1.3.0")

    //Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.13.0")
    implementation("com.google.accompanist:accompanist-coil:0.13.0")

    // Glide
//    implementation("com.github.bumptech.glide:glide:4.10.0")
//    kapt("com.github.bumptech.glide:compiler:4.10.0")
    implementation(Dependencies.glide)

    // Dagger - Hilt
    implementation(Dependencies.hilt_android)
    implementation(Dependencies.hilt_navigation_compose)
    kapt(Dependencies.hilt_compiler)
//    implementation("com.google.dagger:hilt-android:$hilt_version")
//    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")
//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
//    kapt("androidx.hilt:hilt-compiler:$hiltCompilerVersion")
//    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha03")

    // Timber
    implementation(Dependencies.timber)

//    // Firebase Firestore
//    implementation("com.google.firebase:firebase-firestore:$firebaseFirestoreVersion")
//
//    // Firebase Storage KTX
//    implementation("com.google.firebase:firebase-storage-ktx:$firebaseStorageKtxVersion")
//
//    // Firebase Coroutines
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.5.1")

    // ExoPlayer
    api(Dependencies.exoplayer_core)
    api(Dependencies.exoplayer_ui)
    api(Dependencies.exoplayer_extension_mediasession)
}