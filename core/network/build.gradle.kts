plugins {
    alias(libs.plugins.onair.android.library)
    alias(libs.plugins.onair.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "kr.sdbk.network"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain.model)

    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
}