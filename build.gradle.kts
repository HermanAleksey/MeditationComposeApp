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

plugins {
    id("com.autonomousapps.dependency-analysis") version "1.19.0"
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

//task("testAll") {
//
//    println("hello")
//    val listOfAllProjectsNames = mutableListOf<String>()
//    gradle.afterProject {
//        //skip main project
//        if (this.displayName.contains(gradle.rootProject.name))
//            return@afterProject
//
//        //keep only project name in format of :core:database etc.
//        val projectName = this.displayName.let {
//            it.subSequence(9, it.length - 1)
//                .toString()
//        }
//        val a = this.tasks.findByName("$projectName:test")
//        println(this.tasks.asMap)
//
//        a?.let {
//            println("add task: $projectName")
//            dependsOn(
//                it
//            )
//        }
//
//        listOfAllProjectsNames.add(projectName)
//    }
//
//    doLast {
//        println(listOfAllProjectsNames)
//    }
//}