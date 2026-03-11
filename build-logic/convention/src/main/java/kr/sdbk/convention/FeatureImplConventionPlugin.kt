package kr.sdbk.convention

import com.android.build.api.dsl.LibraryExtension
import kr.sdbk.convention.configuration.applyPlugins
import kr.sdbk.convention.configuration.configureUnitTest
import kr.sdbk.convention.configuration.implementFeatureApiSelf
import kr.sdbk.convention.configuration.implementation
import kr.sdbk.convention.configuration.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class FeatureImplConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        applyPlugins(
            libs.plugins.onair.android.library.get().pluginId,
            libs.plugins.onair.compose.get().pluginId,
            libs.plugins.onair.hilt.get().pluginId,
            libs.plugins.kotlin.serialization.get().pluginId
        )

        extensions.configure<LibraryExtension> {
            configureUnitTest(this)
        }

        implementFeatureApiSelf()

        dependencies {
            implementation(project(":core:ui:coordinator"))
            implementation(libs.kotlinx.serialization.json)
        }
    }
}
