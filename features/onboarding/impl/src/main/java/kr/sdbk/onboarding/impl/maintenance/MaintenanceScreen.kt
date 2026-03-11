package kr.sdbk.onboarding.impl.maintenance

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

internal data class MaintenanceEvents(
    val goBack: () -> Unit
)

internal data class MaintenanceUiEvents(
    val onBackPressed: () -> Unit
)

@Composable
internal fun MaintenanceScreen(
    events: MaintenanceEvents,
    viewModel: MaintenanceViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEvents = remember {
        MaintenanceUiEvents(
            onBackPressed = events.goBack
        )
    }

    MaintenanceView(uiState, uiEvents)
}

@Composable
private fun MaintenanceView(
    uiState: MaintenanceState,
    uiEvents: MaintenanceUiEvents
) {

}
