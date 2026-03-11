plugins {
    alias(libs.plugins.onair.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "kr.sdbk.navigation"

    buildFeatures { compose = true }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)

    api(libs.bundles.navigation)
    implementation(libs.kotlinx.serialization.json)
}