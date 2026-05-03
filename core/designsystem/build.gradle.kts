plugins {
    alias(libs.plugins.onair.android.library)
    alias(libs.plugins.onair.compose)
    alias(libs.plugins.onair.hilt)
}

android {
    namespace = "kr.sdbk.designsystem"
}

dependencies {
    implementation(projects.core.androidExtensions)

    implementation(libs.bundles.coil)
}