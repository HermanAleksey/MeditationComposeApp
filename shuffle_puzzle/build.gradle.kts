plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.shuffle_puzzle"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = LibVersions.compose_compiler_version
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(Dependencies.activity_compose)
//    implementation(Dependencies.ac) "androidx.activity:activity-ktx:$activity_ktx_version"
    implementation(Dependencies.core_ktx)
    implementation(Dependencies.compose_ui)
    implementation(Dependencies.compose_material)
    implementation(Dependencies.compose_ui_tooling_preview)

    implementation(Dependencies.accompanist_system_ui_controller)
    implementation(Dependencies.accompanist_pager)
    implementation(Dependencies.accompanist_permissions)

    implementation(Dependencies.canhub_image_cropper)
}