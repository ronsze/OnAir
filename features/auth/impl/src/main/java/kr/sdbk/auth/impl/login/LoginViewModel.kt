package kr.sdbk.auth.impl.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import kr.sdbk.coordinator.viewmodel.BaseViewModel
import kr.sdbk.domain.logic.usecase.auth.LoginUseCase
import kr.sdbk.domain.logic.usecase.auth.SignUpUseCase
import kr.sdbk.domain.model.SocialType

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginState, LoginIntent, LoginEffect>(
    initialState = LoginState()
) {
    override fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.InputEmail -> onInputEmail(intent.email)
            is LoginIntent.InputPassword -> onInputPassword(intent.password)
            is LoginIntent.ClickLogin -> onClickLogin()
            is LoginIntent.ClickSignUp -> onClickSignUp()
            is LoginIntent.ClickSocialLogin -> onClickSocialLogin(intent.socialType)
            is LoginIntent.HandleSocialLoginResult -> handleSocialLoginResult(intent.result, intent.socialType)
        }
    }

    private fun onInputEmail(email: String) {
        updateState { copy(email = email) }
    }

    private fun onInputPassword(password: String) {
        updateState { copy(password = password) }
    }

    private fun onClickLogin() {
        val email = uiState.value.email
        val password = uiState.value.password
        viewModelScope.launch {
            runTask {
                loginUseCase(email, password)
            }.onSuccess {
                sendEffect(LoginEffect.NavigateToHome)
            }.onBasicFailure {
                sendEffect(LoginEffect.ShowLoginFailedToast)
            }
        }
    }

    private fun onClickSignUp() {
        val email = uiState.value.email
        val password = uiState.value.password
        viewModelScope.launch {
            runTask {
                signUpUseCase(email, password)
            }.onSuccess {
                sendEffect(LoginEffect.NavigateToHome)
            }.onBasicFailure {
                sendEffect(LoginEffect.ShowLoginFailedToast)
            }
        }
    }

    private fun onClickSocialLogin(socialType: SocialType) {
        sendEffect(LoginEffect.RequestSocialLogin(socialType))
    }

    private fun handleSocialLoginResult(result: Result<String>, socialType: SocialType) {
        viewModelScope.launch {
            runTask {
                val token = result.getOrThrow()
                loginUseCase(token, socialType)
            }.onSuccess {
                sendEffect(LoginEffect.NavigateToHome)
            }.onBasicFailure {
                Log.e("qweqwe", "$it")
                sendEffect(LoginEffect.ShowLoginFailedToast)
            }
        }
    }
}
