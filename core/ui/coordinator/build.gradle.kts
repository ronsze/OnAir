plugins {
    alias(libs.plugins.onair.android.library)
    alias(libs.plugins.onair.compose)
}

android {
    namespace = "kr.sdbk.ui:coordinator"
}

dependencies {
    implementation(libs.androidx.lifecycle.viewmodel.compose)
}