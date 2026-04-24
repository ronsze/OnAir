import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.onair.android.application)
    alias(libs.plugins.onair.compose)
    alias(libs.plugins.onair.hilt)
    alias(libs.plugins.google.secrets)
    alias(libs.plugins.google.services)
    alias(libs.plugins.google.firebase.crashlytics)
}

val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    namespace = "kr.sdbk.onair"

    defaultConfig {
        applicationId = "kr.sdbk.onair"
        targetSdk = libs.versions.compileSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("config") {
            storeFile = file(keystoreProperties["keyStore"] as String)
            storePassword = keystoreProperties["storePassword"] as String
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs["config"]
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs["config"]
        }
    }

    buildFeatures { buildConfig = true }
}

dependencies {
    implementation(projects.core.ui.coordinator)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.activity.compose)
    implementation(libs.lifecycle.viewmodel.navigation3)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)

    implementation(libs.kakao.login)
    implementation(libs.naver.login)
}