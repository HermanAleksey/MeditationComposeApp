plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp") version LibVersions.ksp_version
}

@Suppress("UnstableApiUsage")
android {
    namespace = "com.justparokq.feature.media_player"
    compileSdk = Config.COMPILE_SDK

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

    implementation(Dependencies.activity_compose)
    implementation(Dependencies.compose_ui)
    implementation(Dependencies.compose_material)
    implementation(Dependencies.compose_ui_tooling)
    implementation(Dependencies.compose_ui_tooling_preview)

    // hilt
    implementation(Dependencies.hilt_android)
    kapt(Dependencies.hilt_compiler)

    // retrofit
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit_converter_gson)
}