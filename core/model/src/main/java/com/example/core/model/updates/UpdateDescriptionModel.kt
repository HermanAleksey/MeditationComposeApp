package com.example.core.model.updates

data class UpdateDescriptionModel(
    val versionName: String,
    val updateReleaseTime: Long,
    val updateTitle: String,
    val updateDescription: String,
    val wasShown: Boolean
)

enum class CompareResult {
    BIGGER, SMALLER, EQUALS
}

data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int,
) {
    fun toString(version: Version) =
        with(version) { "$major.$minor.$patch" }

    fun compare(version: Version): CompareResult {
        if (major > version.major) return CompareResult.BIGGER
        if (major < version.major) return CompareResult.SMALLER

        if (minor > version.minor) return CompareResult.BIGGER
        if (minor < version.minor) return CompareResult.SMALLER

        if (patch > version.patch) return CompareResult.BIGGER
        if (patch < version.patch) return CompareResult.SMALLER

        return CompareResult.EQUALS
    }
}

fun String.toVersion(): Version {
    val versionNumbers = split(".")
    return Version(
        versionNumbers[0].toInt(),
        versionNumbers[1].toInt(),
        versionNumbers[2].toInt(),
    )
}
