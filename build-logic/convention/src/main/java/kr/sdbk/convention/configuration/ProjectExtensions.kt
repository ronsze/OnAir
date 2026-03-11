package kr.sdbk.convention.configuration

import kr.sdbk.convention.configuration.ConventionConstants.DIR_API
import kr.sdbk.convention.configuration.ConventionConstants.DIR_IMPL
import kr.sdbk.convention.configuration.ConventionConstants.GRADLE_FILE
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.implementFeatureApiSelf() {
    dependencies {
        val name = path.replace(DIR_IMPL, DIR_API)
        val path = rootDir.path + name.replace(":", "/")
        val dir = file(path)
        if (dir.exists() && dir.isDirectory && file("${dir.path}/${GRADLE_FILE}").exists() && name.contains(DIR_API)) {
            implementation(project(name))
        }
    }
}