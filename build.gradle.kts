buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${LibVersions.build_gradle_version}")
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

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    id("org.jetbrains.kotlinx.kover") version "0.7.4"
}

allprojects {
    apply(plugin = "org.jetbrains.kotlinx.kover")
}

tasks {
    val type: String by project // type of module
    val nName: String by project // module name
    val tests: String? by project // create test folder?
    val screenName: String? by project // create test folder?

    task("createModule") {
        doFirst {
            if (!project.hasProperty("type")) {
                println("Not provided -Ptype. It should contain project type: feature, core, sample")
                return@doFirst
            }
            if (!project.hasProperty("nName")) {
                println("Not provided -PnName. It should contain module name.")
                return@doFirst
            }
            val moduleType = when (type) {
                "feature" -> ModuleType.FEATURE
                "core" -> ModuleType.CORE
                "sample" -> ModuleType.SAMPLE
                else -> {
                    println("Param -Ptype is not valid, or does not exist")
                    return@doFirst
                }
            }
            val createTestFolder = tests != null
            val createFeatureModuleClasses =
                moduleType == ModuleType.FEATURE && !screenName.isNullOrEmpty()
            val modulePath = "${moduleType.path}/$nName/src/main/java/com.justparokq.$nName"
            val namespace = "com.justparokq.${moduleType.path}.$nName"

            logTaskParams(moduleType.path, nName, createTestFolder, screenName)

            createBaseModuleClasses(
                moduleType = moduleType,
                modulePath = modulePath,
                namespace = namespace,
                featureName = nName,
                createTestFolder = createTestFolder,
            )

            if (createFeatureModuleClasses)
                createMviClasses(
                    namespace = namespace,
                    modulePath = modulePath,
                    screenName = screenName!!
                )
        }
    }
}

enum class ModuleType(val path: String) {
    FEATURE("feature"), CORE("core"), SAMPLE("sample")
}

fun copyTemplate(fileFrom: File, fileTo: File) {
    val contentBytes = fileFrom.let {
        it.createNewFile()
        it.readBytes()
    }

    fileTo.apply {
        createNewFile()
        writeBytes(contentBytes)
    }
}

fun logTaskParams(type: String, nName: String, createTestFolder: Boolean, screenName: String?) {
    println(
        """
                -------------------------------------------
                Creating module.
                type = $type
                module name = $nName
                createTestDir = $createTestFolder
                
                create mvi screen = ${!screenName.isNullOrEmpty()} 
                ${if (!screenName.isNullOrEmpty()) "mvi screen name= $screenName" else "Not specified"}  
                -------------------------------------------
        """.trimIndent()
    )
}

fun createBaseModuleClasses(
    moduleType: ModuleType,
    modulePath: String,
    namespace: String,
    featureName: String,
    createTestFolder: Boolean,
) {
    file(modulePath).mkdirs()

    val screenshotsDirectory = "${moduleType.path}/$featureName/screenshots"
    file(screenshotsDirectory).mkdirs()

    val gitIgnoreFile = "${moduleType.path}/$featureName/.gitignore"
    with(file(gitIgnoreFile)) {
        createNewFile()
        val manifestTemplate = file("$TEMPLATES_DIR_PATH/.gitignore")
        copyTemplate(manifestTemplate, this)
    }

    val buildGradleFile = "${moduleType.path}/$featureName/build.gradle.kts"
    with(file(buildGradleFile)) {
        createNewFile()
        val manifestTemplate = file("$TEMPLATES_DIR_PATH/build.gradle.kts")
        copyTemplate(manifestTemplate, this)

        readText().replace("#{NAMESPACE}#", namespace).let {
            writeText(it)
        }
    }

    val manifestFile = "${moduleType.path}/$featureName/src/main/AndroidManifest.xml"
    with(file(manifestFile)) {
        createNewFile()
        val manifestTemplate = file("$TEMPLATES_DIR_PATH/AndroidManifest.xml")
        copyTemplate(manifestTemplate, this)
    }

    with(file("settings.gradle.kts")) {
        val content = this.readText()
        val includeString = "include(\":${moduleType.path}:$featureName\")"
        writeText("$content\n$includeString")
    }

    if (createTestFolder) {
        val testModulePath =
            "${moduleType.path}/$featureName/src/test/java/com/justparokq/$featureName"
        file(testModulePath).mkdirs()
    }
}

val TEMPLATES_DIR_PATH = "template"
val TEMPLATES_MVI_DIR_PATH = "template/mvi"

fun createMviClasses(namespace: String, modulePath: String, screenName: String) {
    val apiDirectoryPath = "$modulePath/api"
    val internalDirectoryPath = "$modulePath/internal"
    file(apiDirectoryPath).mkdirs()
    file(internalDirectoryPath).mkdirs()

    createFilesFromTemplate(
        newFilePath = "$apiDirectoryPath/$screenName.kt",
        templatePath = "$TEMPLATES_MVI_DIR_PATH/ScreenTemplate",
        screenName = screenName,
        namespace = namespace,
    )

    createFilesFromTemplate(
        newFilePath = apiDirectoryPath + "/${screenName}Action.kt",
        templatePath = "$TEMPLATES_MVI_DIR_PATH/ActionTemplate",
        screenName = screenName,
        namespace = namespace,
    )

    createFilesFromTemplate(
        newFilePath = apiDirectoryPath + "/${screenName}ViewModel.kt",
        templatePath = "$TEMPLATES_MVI_DIR_PATH/ViewModelTemplate",
        screenName = screenName,
        namespace = namespace,
    )

    createFilesFromTemplate(
        newFilePath = apiDirectoryPath + "/${screenName}NavDependenciesTemplate.kt",
        templatePath = "$TEMPLATES_MVI_DIR_PATH/NavDependenciesTemplate",
        screenName = screenName,
        namespace = namespace,
    )

    createFilesFromTemplate(
        newFilePath = "$internalDirectoryPath/Internal$screenName.kt",
        templatePath = "$TEMPLATES_MVI_DIR_PATH/InternalScreenTemplate",
        screenName = screenName,
        namespace = namespace,
    )
}

val SCREEN_NAME_MOCK = "#{SCREEN_NAME}#"
val NAMESPACE_MOCK = "#{NAMESPACE}#"

fun createFilesFromTemplate(
    newFilePath: String,
    templatePath: String,
    screenName: String,
    namespace: String,
) {
    with(file(newFilePath)) {
        createNewFile()
        val template = file(templatePath)
        copyTemplate(template, this)

        readText()
            .replace(SCREEN_NAME_MOCK, screenName)
            .replace(NAMESPACE_MOCK, namespace)
            .let {
                writeText(it)
            }
    }
}
