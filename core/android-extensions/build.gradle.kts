plugins {
    alias(libs.plugins.onair.android.library)
    alias(libs.plugins.onair.hilt)
}

android {
    namespace = "kr.sdbk.android_extensions"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
}
