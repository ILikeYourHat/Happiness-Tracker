import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.axion.release)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.metro)
}

android {
    namespace = "io.github.ilikeyourhat.happinesstracker"

    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "io.github.ilikeyourhat.happinesstracker"
        minSdk = 26
        targetSdk = 36
        versionCode = scmVersion.version.toVersionCode()
        versionName = scmVersion.version
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    signingConfigs {
        create("release") {
            val keystoreFile = rootProject.file("keystore/keystore.jks")
            if (keystoreFile.exists()) {
                storeFile = keystoreFile
                storePassword = System.getenv("ANDROID_KEYSTORE_PASSWORD")
                keyAlias = System.getenv("ANDROID_KEY_ALIAS")
                keyPassword = System.getenv("ANDROID_KEY_PASSWORD")
            } else {
                println("⚠️ Keystore file not found in $keystoreFile, skipping signing")
            }
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "src/androidMain/proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
        allWarningsAsErrors = true
        progressiveMode = true
    }
}

private fun String.toVersionCode(): Int {
    val semVerRegex = """(\d+)\.(\d{1,2})\.(\d{1,2})(\D.*)?""".toRegex()
    val groups = semVerRegex.matchEntire(this)?.destructured?.toList()
        ?: throw GradleException("Version must be in SemVer format, but was $this")
    val (major, minor, patch) = groups.take(3).map { it.toInt() }
    return major * 10000 + minor * 100 + patch
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.work)
    implementation(libs.androidx.room.runtime)
    implementation(project(":composeApp"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
