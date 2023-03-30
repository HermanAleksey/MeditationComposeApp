plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.feature.update_history"
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
    implementation(project(":core:model"))
    implementation(project(":core:data_store"))
    implementation(project(":core:authentication_source"))
    implementation(project(":core:updates_history"))

    implementation(Dependencies.compose_material_icons)

    implementation(Dependencies.activity_compose)
    implementation(Dependencies.compose_ui)
    implementation(Dependencies.compose_material)
    implementation(Dependencies.compose_ui_tooling_preview)
    implementation(Dependencies.compose_ui_tooling)
}