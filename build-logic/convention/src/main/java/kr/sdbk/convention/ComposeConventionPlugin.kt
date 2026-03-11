package kr.sdbk.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import kr.sdbk.convention.configuration.applyPlugins
import kr.sdbk.convention.configuration.implementation
import kr.sdbk.convention.configuration.libs
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        applyPlugins(
            libs.plugins.kotlin.compose.get().pluginId
        )

        val extension = when {
            plugins.hasPlugin(libs.plugins.android.application.get().pluginId) -> extensions.getByType<ApplicationExtension>()
            plugins.hasPlugin(libs.plugins.android.library.get().pluginId) -> extensions.getByType<LibraryExtension>()
            else -> throw GradleException("This plugin should be applied to either an application or library module")
        }

        extension.buildFeatures.compose = true

        dependencies {
            implementation(platform(libs.compose.bom))
            implementation(libs.bundles.compose)
        }
    }
}
