package kr.sdbk.auth.impl.login

import kr.sdbk.coordinator.viewmodel.Effect
import kr.sdbk.coordinator.viewmodel.Intent
import kr.sdbk.coordinator.viewmodel.State
import kr.sdbk.domain.model.SocialType

internal data class LoginState(
    val email: String = "",
    val password: String = ""
) : State

internal sealed interface LoginIntent : Intent {
    data class InputEmail(val email: String) : LoginIntent
    data class InputPassword(val password: String) : LoginIntent
    data object ClickLogin : LoginIntent
    data object ClickSignUp : LoginIntent
    data class ClickSocialLogin(val socialType: SocialType) : LoginIntent
    data class HandleSocialLoginResult(val result: Result<String>, val socialType: SocialType): LoginIntent
}

internal sealed interface LoginEffect : Effect {
    data class RequestSocialLogin(val socialType: SocialType) : LoginEffect
    data object ShowLoginFailedToast : LoginEffect
    data object NavigateToHome : LoginEffect
    data object NavigateToSignUp : LoginEffect
}
