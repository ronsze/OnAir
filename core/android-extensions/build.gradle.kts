plugins {
    alias(libs.plugins.onair.android.library)
}

android {
    namespace = "kr.sdbk.android_extensions"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}
