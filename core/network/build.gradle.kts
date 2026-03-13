plugins {
    alias(libs.plugins.onair.android.library)
    alias(libs.plugins.onair.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.secrets)
}

android {
    namespace = "kr.sdbk.network"

    buildFeatures { buildConfig = true }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain.model)

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