package kr.sdbk.convention

import com.android.build.api.dsl.LibraryExtension
import kr.sdbk.convention.configuration.applyPlugins
import kr.sdbk.convention.configuration.configureKotlinAndroid
import kr.sdbk.convention.configuration.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        applyPlugins(
            libs.plugins.android.library.get().pluginId
        )

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
        }
    }
}