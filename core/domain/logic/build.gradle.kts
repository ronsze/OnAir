plugins {
    alias(libs.plugins.onair.jvm.library)
}

dependencies {
    api(projects.core.domain.model)
}