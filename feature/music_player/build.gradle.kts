plugins {
    id("com.android.library")//application in source
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp") version LibVersions.ksp_version
}

@Suppress("UnstableApiUsage")
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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = LibVersions.compose_compiler_version
    }
    lint {
        baseline = File("lint-baseline.xml")
    }
}

dependencies {
    implementation(project(":core:design_system"))
    implementation(Dependencies.core_ktx)

    implementation(Dependencies.compose_material)
    implementation(Dependencies.compose_ui_tooling)
    implementation(Dependencies.compose_ui_tooling_preview)
    implementation(Dependencies.activity_compose)

    //accompanist
    implementation(Dependencies.accompanist_pager)
    implementation(Dependencies.accompanist_coil)

    //icons
    implementation(Dependencies.compose_material)
    implementation(Dependencies.compose_material_icons)

    implementation(Dependencies.androidx_palette_ktx)

    // Coroutines
    implementation(Dependencies.coroutines_core)

    // Coroutine Lifecycle Scopes
    implementation(Dependencies.lifecycle_runtime_ktx)

    // Coil
    implementation(Dependencies.coil)

    // Glide
    implementation(Dependencies.glide)

    // Dagger - Hilt
    implementation(Dependencies.hilt_android)
    implementation(Dependencies.hilt_navigation_compose)
    kapt(Dependencies.hilt_compiler)

    // Timber
    implementation(Dependencies.timber)

    // ExoPlayer
    implementation(Dependencies.exoplayer_ui)
    implementation(Dependencies.exoplayer_core)
    implementation(Dependencies.exoplayer_extension_mediasession)
}
