package kr.sdbk.support.impl.setting

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kr.sdbk.coordinator.viewmodel.BaseViewModel

@HiltViewModel
internal class SettingsViewModel @Inject constructor() : BaseViewModel<SettingsState, SettingsIntent, SettingsEffect>(
    initialState = SettingsState()
) {
    override fun handleIntent(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.ToggleNotifications -> {
                updateState { copy(notificationsEnabled = intent.enabled) }
            }
            is SettingsIntent.ToggleDarkMode -> {
                updateState { copy(darkModeEnabled = intent.enabled) }
            }
        }
    }
}
