plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp") version LibVersions.ksp_version
}

android {
    namespace = "com.example.feature.authentication"
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

    lint {
        @Suppress("UnstableApiUsage")
        baseline = File("lint-baseline.xml")
    }
}

dependencies {
    implementation(project(":core:design_system"))
    implementation(project(":core:common"))
    implementation(project(":core:data_store"))
    implementation(project(":core:model"))
    implementation(project(":core:authentication_source"))

    implementation(Dependencies.activity_compose)
    implementation(Dependencies.core_ktx)
    implementation(Dependencies.compose_ui)
    implementation(Dependencies.compose_material)
    implementation(Dependencies.compose_ui_tooling)
    implementation(Dependencies.compose_ui_tooling_preview)
    implementation(Dependencies.compose_material_icons)

    //accomponist
    implementation(Dependencies.accompanist_system_ui_controller)
    implementation(Dependencies.accompanist_pager)
    implementation(Dependencies.accompanist_permissions)

    //hilt
    implementation(Dependencies.hilt_navigation_compose)
    implementation(Dependencies.hilt_android)
    kapt(Dependencies.hilt_compiler)

    //glide
    implementation(Dependencies.glide)

    //paging
    implementation(Dependencies.paging_common_ktx)
    implementation(Dependencies.paging_runtime)
    implementation(Dependencies.paging_compose)

    //navigation
    implementation(Dependencies.navigation_compose)
    implementation(Dependencies.raamcosta_compose_destinations_anim_core)
    ksp(Dependencies.raamcosta_compose_destinations_ksp)

    //testing
    testImplementation(project(":core:testing"))
    testImplementation(Dependencies.junit)
    testImplementation (Dependencies.mockito)
    testImplementation (Dependencies.coroutines_test)
    testImplementation (Dependencies.mockito_kotlin)
    testImplementation (Dependencies.powermock_reflect)
    androidTestImplementation(Dependencies.mockito_android)
}