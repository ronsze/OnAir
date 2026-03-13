package kr.sdbk.auth.impl.login

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.sdbk.android_extensions.showToast
import kr.sdbk.auth.impl.login.composable.EmailLoginColumn
import kr.sdbk.auth.impl.login.composable.SocialLoginColumn
import kr.sdbk.auth.impl.login.module.rememberLoginClient
import kr.sdbk.designsystem.component.VerticalSpacer
import kr.sdbk.designsystem.component.WeightSpacer
import kr.sdbk.designsystem.preview.FullScreenPreview
import kr.sdbk.domain.model.SocialType

internal data class LoginEvents(
    val navigateToHome: () -> Unit,
    val navigateToSignUp: () -> Unit
)

internal data class LoginUiEvents(
    val onInputEmail: (String) -> Unit,
    val onInputPassword: (String) -> Unit,
    val onClickLogin: () -> Unit,
    val onClickSignUp: () -> Unit,
    val onClickSocialLogin: (SocialType) -> Unit,
    val onSocialLoginResult: (Result<String>, SocialType) -> Unit
)

@Composable
internal fun LoginScreen(
    events: LoginEvents,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val loginClient = rememberLoginClient()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEvents = remember(viewModel) {
        LoginUiEvents(
            onInputEmail = { viewModel.handleIntent(LoginIntent.InputEmail(it)) },
            onInputPassword = { viewModel.handleIntent(LoginIntent.InputPassword(it)) },
            onClickLogin = { viewModel.handleIntent(LoginIntent.ClickLogin) },
            onClickSignUp = { viewModel.handleIntent(LoginIntent.ClickSignUp) },
            onClickSocialLogin = { viewModel.handleIntent(LoginIntent.ClickSocialLogin(it)) },
            onSocialLoginResult = { result, type -> viewModel.handleIntent(LoginIntent.HandleSocialLoginResult(result, type)) }
        )
    }

    LoginView(uiState, uiEvents)

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is LoginEffect.RequestSocialLogin -> {
                    val res = loginClient.login(context, effect.socialType)
                    uiEvents.onSocialLoginResult(res.first, res.second)
                }
                is LoginEffect.ShowLoginFailedToast -> showToast(context, "실패")
                is LoginEffect.NavigateToHome -> events.navigateToHome()
                is LoginEffect.NavigateToSignUp -> events.navigateToSignUp()
            }
        }
    }
}

@Composable
private fun LoginView(
    uiState: LoginState,
    uiEvents: LoginUiEvents
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WeightSpacer()

        EmailLoginColumn(
            email = uiState.email,
            password = uiState.password,
            onInputEmail = uiEvents.onInputEmail,
            onInputPassword = uiEvents.onInputPassword,
            onClickLogin = uiEvents.onClickLogin,
            onClickSignUp = uiEvents.onClickSignUp
        )
        VerticalSpacer(24.dp)

        SocialLoginColumn(uiEvents.onClickSocialLogin)

        WeightSpacer()
    }
}

@FullScreenPreview
@Composable
private fun Preview() {
    LoginView(
        uiState = LoginState(),
        uiEvents = LoginUiEvents({}, {}, {}, {}, {}, {_,_->})
    )
}
