package kr.sdbk.navigation

import androidx.compose.runtime.Composable

@Composable
fun rememberOnAirNavigator(
    navigationState: OnAirNavigationState
): OnAirNavigator {
    return OnAirNavigator(navigationState)
}

class OnAirNavigator(
    val navigationState: OnAirNavigationState
) {
    fun navigate(dest: OnAirNavKey) {
        navigationState.backStack.add(dest)
    }
    
    fun goBack() {
        navigationState.backStack.removeLastOrNull()
    }
}