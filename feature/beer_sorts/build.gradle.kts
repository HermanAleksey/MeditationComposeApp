plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp") version LibVersions.ksp_version // Depends on your kotlin version
}

android {
    namespace = "com.example.feature.beer_sorts"
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
    implementation(project(":core:punk_source"))

    implementation(Dependencies.compose_ui)
    implementation(Dependencies.compose_material)
    implementation(Dependencies.compose_ui_tooling_preview)

    //hilt
    implementation(Dependencies.hilt_android)
    kapt(Dependencies.hilt_compiler)

    //glide
    implementation(Dependencies.glide)

    //paging
    implementation(Dependencies.paging_common_ktx)
    implementation(Dependencies.paging_runtime)
    implementation(Dependencies.paging_compose)

    //testing
    testImplementation(project(":core:testing"))
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.coroutines_test)
    testImplementation(Dependencies.mockito_kotlin)
}