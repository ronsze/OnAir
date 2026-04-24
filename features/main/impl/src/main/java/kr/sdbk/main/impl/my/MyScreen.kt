package kr.sdbk.main.impl.my

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

internal data object MyEvents

internal data object MyUiEvents

@Composable
internal fun MyScreen(
    events: MyEvents,
    viewModel: MyViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEvents = remember { MyUiEvents }

    MyView(uiState, uiEvents)
}

@Composable
private fun MyView(
    uiState: MyState,
    uiEvents: MyUiEvents
) {

}
