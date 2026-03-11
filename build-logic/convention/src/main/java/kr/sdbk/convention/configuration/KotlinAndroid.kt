package kr.sdbk.convention.configuration

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.api.AndroidBasePlugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension,
) {
    commonExtension.apply {
        compileSdk {
            version = release(libs.versions.compileSdk.get().toInt())
        }

        defaultConfig.apply {
            minSdk {
                minSdk = libs.versions.minSdk.get().toInt()
            }

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    configureKotlin()
}

internal fun Project.configureKotlin() {
    plugins.withType<AndroidBasePlugin> {
        extensions.configure<JavaPluginExtension> {
            toolchain.languageVersion.set(JavaLanguageVersion.of(libs.versions.jdk.get().toInt()))
        }
    }

    plugins.withType<JavaPlugin>().configureEach {
        extensions.configure<JavaPluginExtension> {
            toolchain.languageVersion.set(JavaLanguageVersion.of(libs.versions.jdk.get().toInt()))
        }
    }
}
