package kr.sdbk.convention

import com.android.build.api.dsl.ApplicationExtension
import kr.sdbk.convention.configuration.ConventionConstants.MODULE_TEST_CONFIG
import kr.sdbk.convention.configuration.applyPlugins
import kr.sdbk.convention.configuration.configureKotlinAndroid
import kr.sdbk.convention.configuration.configureUnitTest
import kr.sdbk.convention.configuration.implementCore
import kr.sdbk.convention.configuration.implementFeatureApi
import kr.sdbk.convention.configuration.implementFeatureImpl
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
            configureUnitTest(this)
        }

        implementCore(MODULE_TEST_CONFIG)
        implementFeatureApi()
        implementFeatureImpl()

        dependencies {
            implementation(libs.kotlinx.serialization.json)
        }
    }
}
