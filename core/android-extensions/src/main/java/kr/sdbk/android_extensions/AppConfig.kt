package kr.sdbk.android_extensions

import jakarta.inject.Inject

interface AppConfig {
    val isDebuggable: Boolean
}

internal class AppConfigImpl @Inject constructor() : AppConfig {
    override val isDebuggable: Boolean = BuildConfig.DEBUG
}