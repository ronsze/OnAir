package kr.sdbk.auth.impl.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import kr.sdbk.auth.impl.composable.LoginWebView
import kr.sdbk.auth.impl.composable.rememberLoginModule
import kr.sdbk.designsystem.component.button.BaseButton
import kr.sdbk.designsystem.preview.FullScreenPreview

internal data class LoginEvents(
    val navigateToHome: () -> Unit
)

internal data class LoginUiEvents(
    val onCompleteWebLogin: (code: String, state: String?) -> Unit,
    val onWebLoginFailed: () -> Unit
)

@Composable
internal fun LoginScreen(
    events: LoginEvents,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiEvents = remember(viewModel) {
        LoginUiEvents(
            onCompleteWebLogin = { code, state ->
                viewModel.handleIntent(LoginIntent.CompleteWebLogin(code, state))
            },
            onWebLoginFailed = {
                viewModel.handleIntent(
                    LoginIntent.HandleSocialLoginResult(
                        result = Result.failure(IllegalStateException("웹 로그인 실패"))
                    )
                )
            }
        )
    }

    val loginModule = rememberLoginModule()

    LoginView(
        onClickLogin = loginModule::proceed
    )

    if (loginModule.url.isNotBlank()) {
        LoginWebView(
            url = loginModule.url,
            onLoginComplete = uiEvents.onCompleteWebLogin,
            onLoginFailed = uiEvents.onWebLoginFailed,
        )
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is LoginEffect.NavigateToHome -> events.navigateToHome()
                else -> Unit
            }
        }
    }
}

@Composable
private fun LoginView(
    onClickLogin: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        BaseButton(onClick = onClickLogin) {
            Text(text = "로그인")
        }
    }
}

@FullScreenPreview
@Composable
private fun Preview() {
    LoginView(
        onClickLogin = {}
    )
}
