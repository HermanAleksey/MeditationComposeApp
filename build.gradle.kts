buildscript {
    extra.apply {
        set("activity_ktx_version", "1.6.0")

        set("ktx_version", "1.7.0")
        set("kps_version", "1.7.10-1.0.6")
        set("kotlin_version", "1.7.10")

        set("compose_version", "1.2.1")
        set( "compose_compiler_version" , "1.3.0")

        set("hilt_version","2.42")
        set("hilt_navigation_version", "1.0.0")

        set("navigation_compose_version", "2.5.2")
        set("compose_destination_version", "1.6.20-beta")

        set("splashscreen_version", "1.0.0")

        set("data_store_version", "1.0.0")

        set("bom_version","30.4.1")

        set("accompanist_version", "0.26.4-beta")

        set("retrofit_version", "2.9.0")
        set("okhttp_version", "4.9.3")

        set("canhub_version", "4.3.2")

        set("paging_version", "3.1.1")
        set("paging_compose_version", "1.0.0-alpha16")

        set("glide_version", "2.0.2")

        set("room_version", "2.4.3")
        set("room_coroutines_version", "2.4.3")
    }

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.4")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("com.google.gms:google-services:4.3.13")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
//        jcenter() // Warning: this repository is going to shut down soon
    }
}