plugins {
    `kotlin-dsl`
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    val conventionPlugins = listOf(
        libs.plugins.onair.android.application.get().pluginId to "kr.sdbk.convention.ApplicationConventionPlugin",
        libs.plugins.onair.android.library.get().pluginId to "kr.sdbk.convention.AndroidLibraryConventionPlugin",
        libs.plugins.onair.jvm.library.get().pluginId to "kr.sdbk.convention.JvmLibraryConventionPlugin",
        libs.plugins.onair.feature.api.get().pluginId to "kr.sdbk.convention.FeatureApiConventionPlugin",
        libs.plugins.onair.feature.impl.get().pluginId to "kr.sdbk.convention.FeatureImplConventionPlugin",
        libs.plugins.onair.compose.get().pluginId to "kr.sdbk.convention.ComposeConventionPlugin",
        libs.plugins.onair.hilt.get().pluginId to "kr.sdbk.convention.HiltConventionPlugin"
    )

    plugins {
        conventionPlugins.forEach { registerPlugins(it) }
    }
}

fun NamedDomainObjectContainer<PluginDeclaration>.registerPlugins(plugin: Pair<String, String>) {
    plugin.let { (pluginName, classPath) ->
        register(pluginName) {
            id = pluginName
            implementationClass = classPath
        }
    }
}