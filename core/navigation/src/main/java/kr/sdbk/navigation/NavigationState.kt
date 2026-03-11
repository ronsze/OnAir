package kr.sdbk.navigation

import androidx.compose.runtime.Composable

@Composable
fun rememberOnAirNavigationState(
    startRoute: OnAirNavKey
): OnAirNavigationState {
    return OnAirNavigationState(startRoute)
}

class OnAirNavigationState(
    startRoute: OnAirNavKey
) {
    val backStack: OnAirNavBackStack = OnAirNavBackStack(startRoute)
}