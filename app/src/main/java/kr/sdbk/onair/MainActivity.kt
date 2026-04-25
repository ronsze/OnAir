package kr.sdbk.onair

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import coil3.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kr.sdbk.coordinator.utils.ErrorMonitor
import kr.sdbk.coordinator.utils.LoadingMonitor
import kr.sdbk.designsystem.theme.OnAirTheme
import kr.sdbk.navigation.rememberOnAirNavigationState
import kr.sdbk.navigation.rememberOnAirNavigator
import kr.sdbk.onair.state.rememberOnAirAppState
import kr.sdbk.onboarding.api.SplashRoute

@AndroidEntryPoint
@OptIn(ExperimentalCoilApi::class)
class MainActivity : ComponentActivity() {
    @Inject lateinit var loadingMonitor: LoadingMonitor
    @Inject lateinit var errorMonitor: ErrorMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navigationState = rememberOnAirNavigationState(SplashRoute)
            val navigator = rememberOnAirNavigator(navigationState)
            val appState = rememberOnAirAppState(
                navigator = navigator,
                loadingMonitor = loadingMonitor,
                errorMonitor = errorMonitor
            )

            OnAirTheme {
                OnAirApp(
                    appState = appState,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}