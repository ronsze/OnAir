package kr.sdbk.convention.configuration

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.the

internal val Project.libs get() = the<LibrariesForLibs>()

internal fun Project.applyPlugins(vararg plugins: String) {
    plugins.forEach { apply(plugin = it) }
}

internal fun Project.implementation(dependencyNotation: Any) {
    dependencies.add("implementation", dependencyNotation)
}

internal fun Project.api(dependencyNotation: Any) {
    dependencies.add("api", dependencyNotation)
}
internal fun Project.compileOnly(dependencyNotation: Any) {
    dependencies.add("compileOnly", dependencyNotation)
}

internal fun Project.runtimeOnly(dependencyNotation: Any) {
    dependencies.add("runtimeOnly", dependencyNotation)
}

internal fun Project.testImplementation(dependencyNotation: Any) {
    dependencies.add("testImplementation", dependencyNotation)
}

internal fun Project.androidTestImplementation(dependencyNotation: Any) {
    dependencies.add("androidTestImplementation", dependencyNotation)
}

internal fun Project.debugImplementation(dependencyNotation: Any) {
    dependencies.add("debugImplementation", dependencyNotation)
}

internal fun Project.ksp(dependencyNotation: Any) {
    dependencies.add("ksp", dependencyNotation)
}

internal fun Project.annotationProcessor(dependencyNotation: Any) {
    dependencies.add("annotationProcessor", dependencyNotation)
}