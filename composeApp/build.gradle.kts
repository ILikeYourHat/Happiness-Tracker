import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.axion.release)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.hotReload)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.ksp)
    alias(libs.plugins.metro)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.androidx.lifecycle.viewmodel.compose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.androidx.navigation.compose)
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite.bundled)

            implementation(libs.kotlinx.datetime)
        }
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.work)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.appdirs)
            implementation(libs.kotlinx.coroutines.swing)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "io.github.ilikeyourhat.happinesstracker"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "io.github.ilikeyourhat.happinesstracker"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = scmVersion.version.toVersionCode()
        versionName = scmVersion.version
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

private fun String.toVersionCode(): Int {
    val semVerRegex = """(\d+)\.(\d{1,2})\.(\d{1,2})(\D.*)?""".toRegex()
    val groups = semVerRegex.matchEntire(this)?.destructured?.toList()
        ?: throw GradleException("Version must be in SemVer format, but was $this")
    val (major, minor, patch) = groups.take(3).map { it.toInt() }
    return major * 10000 + minor * 100 + patch
}

room {
    schemaDirectory("$projectDir/schemas")
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom("$rootDir/config/detekt.yml")
}

tasks.withType<Detekt> {
    jvmTarget = "11"
    tasks.getByName("check").dependsOn(this)
    exclude { it.file.path.contains("${File.separator}generated${File.separator}") }
}

dependencies {
    listOf(
        "kspAndroid",
        "kspDesktop",
    ).forEach {
        add(it, libs.androidx.room.compiler)
    }
    detektPlugins(libs.detekt.compose)
    detektPlugins(libs.detekt.formatting)
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "io.github.ilikeyourhat.happinesstracker.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Msi, TargetFormat.Deb)
            packageName = "HappinessTracker"
            packageVersion = scmVersion.undecoratedVersion
            licenseFile.set(rootProject.file("LICENSE"))

            windows {
                iconFile.set(project.file("src/desktopMain/ic_launcher.ico"))
            }
            linux {
                iconFile.set(project.file("src/desktopMain/ic_launcher.png"))
            }
        }
    }
}
