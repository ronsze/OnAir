package kr.sdbk.convention.configuration

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

//  유닛 테스트 관련 의존성 추가 확장 함수
internal fun Project.configureUnitTest(
    commonExtension: CommonExtension
) {
    commonExtension.apply {
        dependencies {
            "testImplementation"(project(":core:test-config"))
        }
    }
}

//  Ui 테스트 관련 의존성 추가 확장 함수
internal fun Project.configureUiTest(
    commonExtension: CommonExtension
) {
    commonExtension.apply {
        dependencies {
            "androidTestImplementation"(libs.compose.ui.test.junit4)
            "debugImplementation"(libs.compose.ui.test.manifest)
        }
    }
}