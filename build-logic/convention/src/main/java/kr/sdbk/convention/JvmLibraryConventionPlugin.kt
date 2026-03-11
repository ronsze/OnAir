package kr.sdbk.convention

import kr.sdbk.convention.configuration.applyPlugins
import kr.sdbk.convention.configuration.configureKotlin
import kr.sdbk.convention.configuration.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        applyPlugins(
            libs.plugins.kotlin.jvm.get().pluginId,
            libs.plugins.java.library.get().pluginId
        )

        extensions.configure<KotlinJvmProjectExtension> {
            configureKotlin()
        }
    }
}
