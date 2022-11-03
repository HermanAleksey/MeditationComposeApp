object Dependencies {
    const val core_ktx = "androidx.core:core-ktx:${LibVersions.ktx_version}"
    const val compose_ui = "androidx.compose.ui:ui:${LibVersions.compose_version}"
    const val compose_material = "androidx.compose.material:material:${LibVersions.compose_version}"
    const val compose_ui_tooling_preview =
        "androidx.compose.ui:ui-tooling-preview:${LibVersions.compose_version}"
    const val lifecycle_runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
    const val activity_compose =
        "androidx.activity:activity-compose:${LibVersions.activity_ktx_version}"
    const val androidx_legacy_support = "androidx.legacy:legacy-support-v4:1.0.0"

    const val compose_material_icons =
        "androidx.compose.material:material-icons-extended:${LibVersions.compose_version}"

    //accompanist
    const val accompanist_system_ui_controller =
        "com.google.accompanist:accompanist-systemuicontroller:${LibVersions.accompanist_version}"
    const val accompanist_pager =
        "com.google.accompanist:accompanist-pager:${LibVersions.accompanist_version}"
    const val accompanist_permissions =
        "com.google.accompanist:accompanist-permissions:${LibVersions.accompanist_version}"

    //firebase
    const val firebase_bom = "com.google.firebase:firebase-bom:${LibVersions.fb_bom_version}"
    const val firebase_analytics_ktx = "com.google.firebase:firebase-analytics-ktx"

    //navigation
    const val navigation_compose =
        "androidx.navigation:navigation-compose:${LibVersions.navigation_compose_version}"
    const val raamcosta_compose_destinations_anim_core =
        "io.github.raamcosta.compose-destinations:animations-core:${LibVersions.compose_destination_version}"
    const val raamcosta_compose_destinations_ksp =
        "io.github.raamcosta.compose-destinations:ksp:${LibVersions.compose_destination_version}"

    //splash screen
    const val splash_screen_core =
        "androidx.core:core-splashscreen:${LibVersions.splashscreen_version}"

    //hilt
    const val hilt_navigation_compose =
        "androidx.hilt:hilt-navigation-compose:${LibVersions.hilt_navigation_version}"
    const val hilt_android = "com.google.dagger:hilt-android:${LibVersions.hilt_version}"
    const val hilt_compiler = "com.google.dagger:hilt-compiler:${LibVersions.hilt_version}"

    //retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${LibVersions.retrofit_version}"
    const val retrofit_converter_gson =
        "com.squareup.retrofit2:converter-gson:${LibVersions.retrofit_version}"

    //okHttp
    const val okhttp = "com.squareup.okhttp3:okhttp:${LibVersions.okhttp_version}"
    const val okhttp_logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${LibVersions.okhttp_version}"

    //data store
    const val datastore_preferences =
        "androidx.datastore:datastore-preferences:${LibVersions.data_store_version}"

    //paging
    const val paging_common_ktx = "androidx.paging:paging-common-ktx:${LibVersions.paging_version}"
    const val paging_runtime = "androidx.paging:paging-runtime:${LibVersions.paging_version}"
    const val paging_compose =
        "androidx.paging:paging-compose:${LibVersions.paging_compose_version}"

    //glide
    const val glide = "com.github.skydoves:landscapist-glide:${LibVersions.glide_version}"

    //image cropper
    const val canhub_image_cropper =
        "com.github.CanHub:Android-Image-Cropper:${LibVersions.canhub_version}"

    //room database
    const val room_runtime = "androidx.room:room-runtime:${LibVersions.room_version}"
    const val room_ktx = "androidx.room:room-ktx:${LibVersions.room_version}"
    const val room_compiler = "androidx.room:room-compiler:${LibVersions.room_version}"

    //testing
    const val junit = "junit:junit:4.13.2"
    const val test_ext_junit = "androidx.test.ext:junit:1.1.3"
    const val espresso_core = "androidx.test.espresso:espresso-core:3.4.0"
    const val ui_test_junit = "androidx.compose.ui:ui-test-junit4:${LibVersions.compose_version}"
    const val ui_tooling = "androidx.compose.ui:ui-tooling:${LibVersions.compose_version}"

}
