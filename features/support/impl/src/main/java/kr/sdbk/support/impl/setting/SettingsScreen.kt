package kr.sdbk.support.impl.setting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

internal data class SettingsEvents(
    val goBack: () -> Unit
)

internal data object SettingsUiEvents

@Composable
internal fun SettingsScreen(
    events: SettingsEvents,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEvents = remember { SettingsUiEvents }

    SettingsView(uiState, uiEvents)
}

@Composable
private fun SettingsView(
    uiState: SettingsState,
    uiEvents: SettingsUiEvents
) {
    Text("Settings Screen")
}
