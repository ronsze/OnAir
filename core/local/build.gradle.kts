plugins {
    alias(libs.plugins.onair.android.library)
    alias(libs.plugins.onair.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "kr.sdbk.local"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain.model)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.datastore)
    implementation(libs.datastore.core)
}