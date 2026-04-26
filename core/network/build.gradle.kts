import java.io.File
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.onair.android.library)
    alias(libs.plugins.onair.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.secrets)
}

val localPropertiesFile: File = rootProject.file("local.properties")
val localProperties = Properties()
localProperties.load(FileInputStream(localPropertiesFile))

android {
    namespace = "kr.sdbk.network"

    buildFeatures { buildConfig = true }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain.logic)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    // Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
}