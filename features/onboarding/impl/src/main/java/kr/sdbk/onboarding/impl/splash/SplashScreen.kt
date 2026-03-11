package kr.sdbk.onboarding.impl.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

internal data class SplashEvents(
    val navigateToHome: () -> Unit
)

internal data class SplashUiEvents(
    val onPermissionHandle: (Boolean) -> Unit
)

@Composable
internal fun SplashScreen(
    events: SplashEvents,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEvents = remember {
        SplashUiEvents(
            onPermissionHandle = { viewModel.handleIntent(SplashIntent.PermissionHandled(it)) }
        )
    }

    SplashView(uiState, uiEvents)

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SplashEffect.NavigateToHome -> events.navigateToHome()
            }
        }
    }
}

@Composable
private fun SplashView(
    uiState: SplashState,
    uiEvents: SplashUiEvents
) {

}