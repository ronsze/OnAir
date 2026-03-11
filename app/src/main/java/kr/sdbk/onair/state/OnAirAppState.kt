package kr.sdbk.onair.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
internal fun rememberOnAirAppState(): OnAirAppState {
    val state = remember {
        OnAirAppState()
    }

    return state
}

internal class OnAirAppState