package kr.sdbk.onair.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kr.sdbk.navigation.OnAirNavigator

@Composable
internal fun rememberOnAirAppState(
    navigator: OnAirNavigator
): OnAirAppState {
    val state = remember(navigator) {
        OnAirAppState(navigator)
    }

    return state
}

internal class OnAirAppState(
    val navigator: OnAirNavigator
) {

}