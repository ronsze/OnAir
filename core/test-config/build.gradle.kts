plugins {
    alias(libs.plugins.onair.android.library)
    alias(libs.plugins.onair.hilt)
}

android {
    namespace = "kr.sdbk.test_config"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(projects.core.ui.coordinator)

    api(libs.junit)
    api(libs.androidx.test.core)
    api(libs.androidx.test.ext.junit)
    api(libs.androidx.espresso.core)
    api(libs.kotlinx.coroutines.test)
    api(libs.robolectric)

    api(libs.mockk)
    api(libs.mockk.android)
    api(libs.turbine)
}