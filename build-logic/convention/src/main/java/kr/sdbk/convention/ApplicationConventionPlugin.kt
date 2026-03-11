package kr.sdbk.convention

import com.android.build.api.dsl.ApplicationExtension
import kr.sdbk.convention.configuration.applyPlugins
import kr.sdbk.convention.configuration.configureKotlinAndroid
import kr.sdbk.convention.configuration.implementation
import kr.sdbk.convention.configuration.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        applyPlugins(
            libs.plugins.android.application.get().pluginId
        )

        extensions.configure<ApplicationExtension> {
            configureKotlinAndroid(this)
        }

        dependencies {
            implementation(libs.kotlinx.serialization.json)
        }
    }
}
