plugins {
    alias(libs.plugins.onair.android.application)
    alias(libs.plugins.onair.compose)
    alias(libs.plugins.onair.hilt)
}

android {
    namespace = "kr.sdbk.onair"

    defaultConfig {
        applicationId = "kr.sdbk.onair"
        targetSdk = libs.versions.compileSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.core.ui.coordinator)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.activity.compose)
    implementation(libs.lifecycle.viewmodel.navigation3)
}