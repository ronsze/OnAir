package kr.sdbk.onair

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kr.sdbk.main.api.TopLevelDestinations
import kr.sdbk.onair.composable.MainBottomBar
import kr.sdbk.onair.navigation.OnAirNavHost
import kr.sdbk.onair.state.OnAirAppState

@Composable
internal fun OnAirApp(
    appState: OnAirAppState,
    modifier: Modifier = Modifier
) {
    val currentDestination = appState.navigator.navigationState.backStack.lastOrNull()

    Scaffold(
        bottomBar = {
            if (currentDestination != null && currentDestination is TopLevelDestinations) {
                MainBottomBar(
                    currentDestination = currentDestination,
                    onSelectTab = appState.navigator::navigate
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        OnAirNavHost(
            navigator = appState.navigator,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
