plugins {
    id("com.android.library")//application in source
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")//?
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp") version LibVersions.ksp_version // Depends on your kotlin version
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
    implementation(Dependencies.core_ktx)

    implementation(Dependencies.compose_material)
    implementation(Dependencies.compose_ui_tooling_preview)
    implementation(Dependencies.activity_compose)
    implementation("com.google.android.material:material:1.4.0")
    val constraintlayout_compose_version = "1.0.1"
    implementation("androidx.constraintlayout:constraintlayout-compose:$constraintlayout_compose_version")

    //accompanist
    implementation(Dependencies.accompanist_pager)
    //todo add?
//    implementation("com.google.accompanist:accompanist-insets:${LibVersions.accompanist_version}")

    //icons
    implementation(Dependencies.compose_material)
    implementation(Dependencies.compose_material_icons)

    val palette_ktx_version = "1.0.0"
    implementation("androidx.palette:palette-ktx:$palette_ktx_version")

    // Coroutines
    implementation(Dependencies.coroutines_core)

    // Coroutine Lifecycle Scopes
    implementation(Dependencies.lifecycle_runtime_ktx)

    // Coil
    val coil_version = "1.3.0"
    implementation("io.coil-kt:coil:$coil_version")

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

    // Timber
    implementation(Dependencies.timber)

    // ExoPlayer
    val exo_player_version = "2.18.6"
    api("com.google.android.exoplayer:exoplayer-core:$exo_player_version")
    api("com.google.android.exoplayer:exoplayer-ui:$exo_player_version")
    api("com.google.android.exoplayer:extension-mediasession:$exo_player_version")

    //tests
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.0-rc01")
}
