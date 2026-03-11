pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "OnAir"

include(":app")

include(":core:common")
include(":core:designsystem")
include(":core:navigation")
include(":core:data")
include(":core:network")
include(":core:local")

include(":core:domain:logic")
include(":core:domain:model")

include(":features:onboarding:impl")
include(":features:onboarding:api")

include(":features:main:api")
include(":features:main:impl")

include(":features:support:api")
include(":features:support:impl")

include(":core:ui:coordinator")
include(":core:test-config")
