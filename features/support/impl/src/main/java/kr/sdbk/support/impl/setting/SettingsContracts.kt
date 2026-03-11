package kr.sdbk.support.impl.setting

import kr.sdbk.coordinator.viewmodel.Effect
import kr.sdbk.coordinator.viewmodel.Intent
import kr.sdbk.coordinator.viewmodel.State

internal data class SettingsState(
    val notificationsEnabled: Boolean = true,
    val darkModeEnabled: Boolean = false
) : State

internal sealed interface SettingsIntent : Intent {
    data class ToggleNotifications(val enabled: Boolean) : SettingsIntent
    data class ToggleDarkMode(val enabled: Boolean) : SettingsIntent
}

internal sealed interface SettingsEffect : Effect
