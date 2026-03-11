package kr.sdbk.convention

import kr.sdbk.convention.configuration.api
import kr.sdbk.convention.configuration.applyPlugins
import kr.sdbk.convention.configuration.implementation
import kr.sdbk.convention.configuration.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeatureApiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        applyPlugins(
            libs.plugins.onair.android.library.get().pluginId,
            libs.plugins.kotlin.serialization.get().pluginId
        )

        dependencies {
            api(project(":core:navigation"))
            implementation(libs.kotlinx.serialization.json)
        }
    }
}
