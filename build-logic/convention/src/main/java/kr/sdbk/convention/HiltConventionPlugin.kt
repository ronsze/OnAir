package kr.sdbk.convention

import kr.sdbk.convention.configuration.applyPlugins
import kr.sdbk.convention.configuration.implementation
import kr.sdbk.convention.configuration.ksp
import kr.sdbk.convention.configuration.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        applyPlugins(
            libs.plugins.hilt.get().pluginId,
            libs.plugins.ksp.get().pluginId
        )

        dependencies {
            implementation(libs.hilt)
        }

        pluginManager.withPlugin(libs.plugins.ksp.get().pluginId) {
            dependencies {
                ksp(libs.hilt.compiler)
            }
        }

        pluginManager.withPlugin(libs.plugins.kotlin.compose.get().pluginId) {
            dependencies {
                implementation(libs.hilt.navigation)
            }
        }
    }
}