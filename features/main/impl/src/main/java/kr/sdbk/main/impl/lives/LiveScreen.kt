package kr.sdbk.main.impl.lives

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

internal data object LiveEvents

internal data object LiveUiEvents

@Composable
internal fun LiveScreen(
    events: LiveEvents,
    viewModel: LiveViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEvents = remember { LiveUiEvents }

    LiveView(uiState, uiEvents)
}

@Composable
private fun LiveView(
    uiState: LiveState,
    uiEvents: LiveUiEvents
) {

}
