pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven ( url = "https://jitpack.io" )
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven ( url = "https://jitpack.io" )
    }
}

rootProject.name = "MeditationComposeApp"
include(":app", ":shuffle_puzzle", ":buildsrc")
