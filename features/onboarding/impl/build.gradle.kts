plugins {
    alias(libs.plugins.onair.feature.impl)
}

android {
    namespace = "kr.sdbk.onboarding.impl"
}

dependencies {
    implementation(projects.features.main.api)
}