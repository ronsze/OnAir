package kr.sdbk.convention.configuration

import kr.sdbk.convention.configuration.ConventionConstants.DIR_API
import kr.sdbk.convention.configuration.ConventionConstants.DIR_CORE
import kr.sdbk.convention.configuration.ConventionConstants.DIR_FEATURE
import kr.sdbk.convention.configuration.ConventionConstants.DIR_IMPL
import kr.sdbk.convention.configuration.ConventionConstants.GRADLE_FILE
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.implementCore(vararg exclude: String) {
    val coreDirs = rootDir.resolve(DIR_CORE).listFiles()

    dependencies {
        coreDirs
            ?.filter { it.isDirectory && file("${it.path}/$GRADLE_FILE").exists() && !exclude.toList().contains(it.name) }
            ?.forEach { implementation(project(":$DIR_CORE:${it.name}")) }

        coreDirs
            ?.filter { file("${it.path}/$DIR_API").exists() }
            ?.filter { it.isDirectory && file("${it.path}/$DIR_API/$GRADLE_FILE").exists() && !exclude.toList().contains(it.name) }
            ?.forEach { dependencies { implementation(project(":$DIR_CORE:${it.name}:$DIR_API")) } }

        coreDirs
            ?.filter { file("${it.path}/$DIR_IMPL").exists() }
            ?.filter { it.isDirectory && file("${it.path}/$DIR_IMPL/$GRADLE_FILE").exists() && !exclude.toList().contains(it.name) }
            ?.forEach { dependencies { implementation(project(":$DIR_CORE:${it.name}:$DIR_IMPL")) } }
    }
}

internal fun Project.implementFeatureApi(vararg exclude: String) {
    dependencies {
        rootDir.resolve(DIR_FEATURE).listFiles()
            ?.filter { file("${it.path}/$DIR_API").exists() }
            ?.filter { it.isDirectory && file("${it.path}/$DIR_API/${GRADLE_FILE}").exists() && !exclude.toList().contains(it.name) }
            ?.forEach { implementation(project(":$DIR_FEATURE:${it.name}:$DIR_API")) }
    }
}

internal fun Project.implementFeatureImpl(vararg exclude: String) {
    dependencies {
        rootDir.resolve(DIR_FEATURE).listFiles()
            ?.filter { file("${it.path}/$DIR_IMPL").exists() }
            ?.filter { it.isDirectory && file("${it.path}/$DIR_IMPL/${GRADLE_FILE}").exists() && !exclude.toList().contains(it.name) }
            ?.forEach { implementation(project(":$DIR_FEATURE:${it.name}:$DIR_IMPL")) }
    }
}

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