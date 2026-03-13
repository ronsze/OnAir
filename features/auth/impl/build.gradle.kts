plugins {
    alias(libs.plugins.onair.feature.impl)
    alias(libs.plugins.google.secrets)
}

android {
    namespace = "kr.sdbk.auth.impl"

    buildFeatures { buildConfig = true }
}

dependencies {
    implementation(projects.features.main.api)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    
    // Social Login
    implementation(libs.kakao.login)
    implementation(libs.naver.login)
    implementation(libs.bundles.google.login)
}