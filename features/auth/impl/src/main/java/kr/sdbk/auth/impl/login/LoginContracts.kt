package kr.sdbk.auth.impl.login

import kr.sdbk.coordinator.viewmodel.Effect
import kr.sdbk.coordinator.viewmodel.Intent
import kr.sdbk.coordinator.viewmodel.State

internal data class LoginState(
    val email: String = "",
    val password: String = ""
) : State

internal sealed interface LoginIntent : Intent {
    data class CompleteWebLogin(val code: String, val state: String?) : LoginIntent
    data class HandleSocialLoginResult(val result: Result<String>) : LoginIntent
}

internal sealed interface LoginEffect : Effect {
    data object ShowLoginFailedToast : LoginEffect
    data object NavigateToHome : LoginEffect
}
