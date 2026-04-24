package kr.sdbk.onair

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.sdbk.main.api.TopLevelDestinations
import kr.sdbk.onair.composable.MainBottomBar
import kr.sdbk.onair.navigation.OnAirNavHost
import kr.sdbk.onair.state.OnAirAppState
import kr.sdbk.onair.ui.HandleLoading
import kr.sdbk.onair.ui.error.HandleError

@Composable
internal fun OnAirApp(
    appState: OnAirAppState,
    modifier: Modifier = Modifier
) {
    val currentDestination = appState.navigator.navigationState.backStack.lastOrNull()

    Surface {
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

        val onLoading by appState.onLoading.collectAsStateWithLifecycle()
        val error by appState.error.collectAsStateWithLifecycle()

        HandleLoading(onLoading)
        HandleError(error, appState::dismissError)
    }
}
