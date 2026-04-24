plugins {
    alias(libs.plugins.onair.jvm.library)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(projects.core.domain.model)
    implementation(libs.kotlinx.serialization.json)
}