import org.gradle.api.JavaVersion

object Config {
    const val APPLICATION_ID = "com.example.meditationcomposeapp"

    const val MAJOR_VERSION = 0
    const val MINOR_VERSION = 8
    const val PATCH_VERSION = 2
    const val VERSION_NAME = "$MAJOR_VERSION.$MINOR_VERSION.$PATCH_VERSION"
    const val VERSION_CODE = 14

    const val MIN_SDK = 26
    const val TARGET_SDK = 33
    const val COMPILE_SDK = 33
}