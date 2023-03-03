plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.core.testing"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //testing
    implementation(Dependencies.junit)
    implementation(Dependencies.mockito)
    implementation(Dependencies.coroutines_test)
    implementation(Dependencies.mockito_kotlin)
    implementation(Dependencies.powermock_reflect)
}