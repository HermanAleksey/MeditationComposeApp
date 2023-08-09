plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.justparokq.core.punk_source"
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
        buildConfigField("String", "PUNK_API_URL", "\"https://api.punkapi.com/v2/\"")
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
    api(project(":core:network"))
    api(project(":core:database"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(Dependencies.coroutines_core)

    //hilt
    implementation(Dependencies.hilt_android)
    kapt(Dependencies.hilt_compiler)

    //retrofit
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit_converter_gson)

    //paging
    implementation(Dependencies.paging_common_ktx)
    implementation(Dependencies.paging_runtime)
    implementation(Dependencies.paging_compose)
}