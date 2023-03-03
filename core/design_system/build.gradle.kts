plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.core.design_system"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33
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
    implementation(Dependencies.compose_ui)
    implementation(Dependencies.compose_material)
    implementation(Dependencies.compose_ui_tooling_preview)
}