plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") version LibVersions.ksp_version // Depends on your kotlin version
}

android {
    namespace = "com.example.meditationcomposeapp"
    compileSdk = 33

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionName = Config.versionName
        versionCode = Config.versionCode

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add("default")

    productFlavors {
        create("demo") {
            buildConfigField("boolean", "ENABLE_VALIDATION", "false")
        }
        create("full") {
            buildConfigField("boolean", "ENABLE_VALIDATION", "true")
        }
    }
    kapt {
        correctErrorTypes = true
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
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/${name}/kotlin")
            }
        }
    }
}

dependencies {
//    implementation(project(":feature:authentication"))
    implementation(project(":feature:beer_sorts"))
    implementation(project(":feature:shuffle_puzzle"))
    implementation(project(":feature:authentication"))

    implementation(project(":core:authentication_source"))
    implementation(project(":core:updates_history"))
    implementation(project(":core:updates_history"))
    implementation(project(":core:data_store"))
    implementation(project(":core:updates_history"))
    implementation(project(":core:design_system"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))

    implementation(Dependencies.core_ktx)
    implementation(Dependencies.compose_ui)
    implementation(Dependencies.compose_material)
    implementation(Dependencies.compose_ui_tooling_preview)
    implementation(Dependencies.lifecycle_runtime_ktx)
    implementation(Dependencies.activity_compose)
    implementation(Dependencies.androidx_legacy_support)

    implementation(Dependencies.compose_material_icons)

    //animations
    implementation(Dependencies.accompanist_system_ui_controller)

    //firebase
    implementation(platform(Dependencies.firebase_bom))
    implementation(Dependencies.firebase_analytics_ktx)

    //navigation
    implementation(Dependencies.navigation_compose)
    implementation(Dependencies.raamcosta_compose_destinations_anim_core)
    ksp(Dependencies.raamcosta_compose_destinations_ksp)
    testImplementation(Dependencies.testng)

    //splash screen
    implementation(Dependencies.splash_screen_core)

    //hilt
    implementation(Dependencies.hilt_navigation_compose)
    implementation(Dependencies.hilt_android)
    kapt(Dependencies.hilt_compiler)

    //Gson
    implementation(Dependencies.gson)

    //retrofit
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit_converter_gson)

    //okHttp
    implementation(Dependencies.okhttp)
    implementation(Dependencies.okhttp_logging_interceptor)

    //data store
    implementation(Dependencies.datastore_preferences)

    //paging
    implementation(Dependencies.paging_common_ktx)
    implementation(Dependencies.paging_runtime)
    implementation(Dependencies.paging_compose)

    //glide
    implementation(Dependencies.glide)

    //room database
    implementation(Dependencies.room_runtime)
    implementation(Dependencies.room_ktx)
    kapt(Dependencies.room_compiler)
    implementation(Dependencies.room_pager)

    implementation(Dependencies.timber)

    //testing
    testImplementation(Dependencies.junit)
    testImplementation (Dependencies.mockito)
    testImplementation (Dependencies.coroutines_test)
    testImplementation (Dependencies.mockito_kotlin)
    testImplementation (Dependencies.powermock_reflect)
    androidTestImplementation(Dependencies.mockito_android)
}
