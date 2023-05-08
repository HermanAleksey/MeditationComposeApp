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

    implementation(Dependencies.compose_material)
    implementation(Dependencies.compose_ui_tooling_preview)
    implementation(Dependencies.activity_compose)

    implementation(Dependencies.compose_material_icons)

    // animations
    implementation(Dependencies.accompanist_system_ui_controller)

    // navigation
    implementation(Dependencies.raamcosta_compose_destinations_anim_core)
    ksp(Dependencies.raamcosta_compose_destinations_ksp)

    // splash screen
    implementation(Dependencies.splash_screen_core)

    // hilt
    implementation(Dependencies.hilt_navigation_compose)
    implementation(Dependencies.hilt_android)
    kapt(Dependencies.hilt_compiler)

    //coil
    implementation("io.coil-kt:coil-compose:2.3.0")

    // Coroutines
    implementation(Dependencies.coroutines_core)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${LibVersions.coroutines_version}")

//    implementation("com.google.dagger:hilt-android:$hilt_version")
//    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")
//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
//    kapt("androidx.hilt:hilt-compiler:$hiltCompilerVersion")
//    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha03")

    // ExoPlayer
    api(Dependencies.exoplayer_core)
    api(Dependencies.exoplayer_ui)
    api(Dependencies.exoplayer_extension_mediasession)

    implementation(Dependencies.timber)
}
