plugins {
    alias(libs.plugins.onair.android.library)
    alias(libs.plugins.onair.hilt)
}

android {
    namespace = "kr.sdbk.data"
}

dependencies {
    implementation(projects.core.domain.logic)
}