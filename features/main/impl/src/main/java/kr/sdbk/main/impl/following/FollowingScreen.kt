package kr.sdbk.main.impl.following

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

internal data object FollowingEvents

internal data object FollowingUiEvents

@Composable
internal fun FollowingScreen(
    events: FollowingEvents,
    viewModel: FollowingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEvents = remember { FollowingUiEvents }

    FollowingView(uiState, uiEvents)
}

@Composable
private fun FollowingView(
    uiState: FollowingState,
    uiEvents: FollowingUiEvents
) {

}
