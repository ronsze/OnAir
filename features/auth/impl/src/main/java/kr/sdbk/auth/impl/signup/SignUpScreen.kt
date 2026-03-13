package kr.sdbk.auth.impl.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

internal data class SignUpEvents(
    val onBackPressed: () -> Unit
)

internal data class SignUpUiEvents(
    val onInputEmail: (String) -> Unit,
    val onInputPassword: (String) -> Unit,
    val onInputConfirmPassword: (String) -> Unit,
    val onClickSignUp: () -> Unit
)

@Composable
internal fun SignUpScreen(
    events: SignUpEvents,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEvents = remember(viewModel) {
        SignUpUiEvents(
            onInputEmail = { viewModel.handleIntent(SignUpIntent.InputEmail(it)) },
            onInputPassword = { viewModel.handleIntent(SignUpIntent.InputPassword(it)) },
            onInputConfirmPassword = { viewModel.handleIntent(SignUpIntent.InputConfirmPassword(it)) },
            onClickSignUp = { viewModel.handleIntent(SignUpIntent.ClickSignUp) }
        )
    }

    SignUpView(uiState, uiEvents)
}

@Composable
private fun SignUpView(
    uiState: SignUpState,
    uiEvents: SignUpUiEvents
) {

}
