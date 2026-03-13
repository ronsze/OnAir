package kr.sdbk.test_config

import org.junit.Rule

abstract class BaseUnitTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
}