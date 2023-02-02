plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.core.updates_history"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        buildConfigField("String", "SERVER_URL", "\"https://myserver:8080/v1/\"")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    api(project(":core:database"))
    api(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(Dependencies.core_ktx)
    implementation(Dependencies.coroutines_core)

    //gson converter
    implementation(Dependencies.retrofit_converter_gson)

    //hilt
    implementation(Dependencies.hilt_android)
    kapt(Dependencies.hilt_compiler)

    //room database
    implementation(Dependencies.room_runtime)
    implementation(Dependencies.room_ktx)
    kapt(Dependencies.room_compiler)
    implementation(Dependencies.room_pager)
}