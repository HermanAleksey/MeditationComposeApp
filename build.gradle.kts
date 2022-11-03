buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${LibVersions.tool_build_gradle}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${LibVersions.hilt_version}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${LibVersions.kotlin_gradle_plugin}")
        classpath("com.google.gms:google-services:${LibVersions.gms_google_services}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}