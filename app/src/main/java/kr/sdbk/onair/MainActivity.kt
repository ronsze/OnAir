package kr.sdbk.onair

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.designsystem.theme.OnAirTheme
import kr.sdbk.navigation.rememberOnAirNavigationState
import kr.sdbk.navigation.rememberOnAirNavigator
import kr.sdbk.onair.state.rememberOnAirAppState
import kr.sdbk.onboarding.api.SplashRoute

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigationState = rememberOnAirNavigationState(SplashRoute)
            val navigator = rememberOnAirNavigator(navigationState)
            val appState = rememberOnAirAppState(
                navigator = navigator
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