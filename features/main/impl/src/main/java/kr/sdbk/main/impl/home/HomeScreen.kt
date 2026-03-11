package kr.sdbk.main.impl.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

internal data object HomeEvents

internal data object HomeUiEvents

@Composable
internal fun HomeScreen(
    events: HomeEvents,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEvents = remember { HomeUiEvents }

    HomeView(uiState, uiEvents)
}

@Composable
private fun HomeView(
    uiState: HomeState,
    uiEvents: HomeUiEvents
) {

}
