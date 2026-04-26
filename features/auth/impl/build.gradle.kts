import java.io.File
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.onair.feature.impl)
    alias(libs.plugins.google.secrets)
}

val localPropertiesFile: File = rootProject.file("local.properties")
val localProperties = Properties().apply {
    load(FileInputStream(localPropertiesFile))
}

android {
    namespace = "kr.sdbk.auth.impl"

    buildFeatures { buildConfig = true }
}

dependencies {
    implementation(projects.features.main.api)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    // Social Login
    implementation(libs.kakao.login)
    implementation(libs.naver.login)
    implementation(libs.bundles.google.login)
}
