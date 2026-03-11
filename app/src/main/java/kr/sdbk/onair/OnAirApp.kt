package kr.sdbk.onair

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kr.sdbk.onair.navigation.OnAirNavHost
import kr.sdbk.onair.state.OnAirAppState

/**
 * Main app entry point with Navigation 3 integration
 */
@Composable
internal fun OnAirApp(
    appState: OnAirAppState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        OnAirNavHost(
            navigator = appState.navigator,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
