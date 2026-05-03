package kr.sdbk.auth.impl.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import kr.sdbk.coordinator.model.Error
import kr.sdbk.coordinator.viewmodel.BaseViewModel
import kr.sdbk.domain.logic.usecase.user_auth.LoginUseCase

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginState, LoginIntent, LoginEffect>(
    initialState = LoginState()
) {
    override fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.CompleteWebLogin -> onCompleteWebLogin(intent.code)
            is LoginIntent.HandleSocialLoginResult -> handleSocialLoginResult(intent.result)
        }
    }

    private fun onCompleteWebLogin(code: String) {
        viewModelScope.launch {
            runTask {
                loginUseCase(code)
            }.onSuccess {
                sendEffect(LoginEffect.NavigateToHome)
            }.onBasicFailure {
                errorMonitor.update(Error.Default(it.message ?: "로그인에 실패했습니다."))
                sendEffect(LoginEffect.ShowLoginFailedToast)
            }
        }
    }

    private fun handleSocialLoginResult(result: Result<String>) {
        result.onFailure {
            errorMonitor.update(Error.Default(it.message ?: "웹 로그인에 실패했습니다."))
            sendEffect(LoginEffect.ShowLoginFailedToast)
        }
    }
}
