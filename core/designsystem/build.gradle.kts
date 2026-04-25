plugins {
    alias(libs.plugins.onair.android.library)
    alias(libs.plugins.onair.compose)
}

android {
    namespace = "kr.sdbk.designsystem"
}

dependencies {
    implementation(libs.coil.compose)
}