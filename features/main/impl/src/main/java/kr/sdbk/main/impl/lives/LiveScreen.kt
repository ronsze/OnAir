package kr.sdbk.main.impl.lives

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.sdbk.domain.model.live.Live
import kr.sdbk.main.impl.lives.composable.LiveList

internal data class LiveEvents(
    val navigateToLiveDetail: (Live) -> Unit,
    val navigateToSearch: (tag: String) -> Unit
)

internal data class LiveUiEvents(
    val onSelectLive: (Live) -> Unit,
    val onSelectTag: (String) -> Unit,
)

@Composable
internal fun LiveScreen(
    events: LiveEvents,
    viewModel: LiveViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEvents = remember {
        LiveUiEvents(
            onSelectLive = { events.navigateToLiveDetail(it) },
            onSelectTag = { events.navigateToSearch(it) },
        )
    }

    LiveView(uiState, uiEvents)
}

@Composable
private fun LiveView(
    uiState: LiveState,
    uiEvents: LiveUiEvents
) {
    Column {
        LiveList(
            lives = uiState.lives,
            onSelectLive = uiEvents.onSelectLive,
            onSelectTag = uiEvents.onSelectTag
        )
    }
}
