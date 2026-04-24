package kr.sdbk.onair.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kr.sdbk.coordinator.model.Error
import kr.sdbk.coordinator.model.LoadingState
import kr.sdbk.coordinator.utils.ErrorMonitor
import kr.sdbk.coordinator.utils.LoadingMonitor
import kr.sdbk.navigation.OnAirNavigator

@Composable
internal fun rememberOnAirAppState(
    navigator: OnAirNavigator,
    loadingMonitor: LoadingMonitor,
    errorMonitor: ErrorMonitor
): OnAirAppState {
    val scope = rememberCoroutineScope()

    val state = remember(
        scope,
        navigator,
        loadingMonitor,
        errorMonitor
    ) {
        OnAirAppState(
            scope,
            navigator,
            loadingMonitor,
            errorMonitor
        )
    }

    return state
}

internal class OnAirAppState(
    scope: CoroutineScope,
    val navigator: OnAirNavigator,
    loadingMonitor: LoadingMonitor,
    private val errorMonitor: ErrorMonitor
) {
    val onLoading: StateFlow<Boolean> = loadingMonitor.data.map {
        it == LoadingState.ON
    }.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )

    val error: StateFlow<Error?> = errorMonitor.data.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun dismissError() {
        errorMonitor.update(null)
    }
}