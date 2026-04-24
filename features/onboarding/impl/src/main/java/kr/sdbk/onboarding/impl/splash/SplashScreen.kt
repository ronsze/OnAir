package kr.sdbk.onboarding.impl.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kr.sdbk.designsystem.preview.FullScreenPreview

internal data class SplashEvents(
    val navigateToLogin: () -> Unit,
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
                is SplashEffect.NavigateToLogin -> events.navigateToLogin()
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
    Box(Modifier.fillMaxSize()) {
        Text(text = "Splash", modifier = Modifier.align(Alignment.Center))
    }

    LaunchedEffect(Unit) {
        delay(2000)
        uiEvents.onPermissionHandle(true)
    }
}

@FullScreenPreview
@Composable
private fun Preview() {
    SplashView(
        SplashState,
        SplashUiEvents({})
    )
}
