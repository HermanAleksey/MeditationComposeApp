plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp") version LibVersions.ksp_version // Depends on your kotlin version
}

android {
    namespace = "com.example.feature.shuffle_puzzle"
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

    implementation(Dependencies.activity_compose)
    implementation(Dependencies.compose_ui)
    implementation(Dependencies.compose_material)
    implementation(Dependencies.compose_ui_tooling_preview)
    implementation(Dependencies.compose_ui_tooling)

    implementation(Dependencies.accompanist_pager)

    implementation(Dependencies.canhub_image_cropper)

    //hilt
    implementation(Dependencies.hilt_android)
    kapt(Dependencies.hilt_compiler)

    //testing
    testImplementation(project(":core:testing"))
    testImplementation(Dependencies.junit)
    testImplementation (Dependencies.mockito)
    testImplementation (Dependencies.coroutines_test)
    testImplementation (Dependencies.powermock_reflect)
}