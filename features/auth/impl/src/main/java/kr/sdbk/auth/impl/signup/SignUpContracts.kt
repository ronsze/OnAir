package kr.sdbk.auth.impl.signup

import kr.sdbk.coordinator.viewmodel.Effect
import kr.sdbk.coordinator.viewmodel.Intent
import kr.sdbk.coordinator.viewmodel.State

internal data class SignUpState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
) : State

internal sealed interface SignUpIntent : Intent {
    data class InputEmail(val email: String) : SignUpIntent
    data class InputPassword(val password: String) : SignUpIntent
    data class InputConfirmPassword(val confirmPassword: String) : SignUpIntent
    data object ClickSignUp : SignUpIntent
}

internal sealed interface SignUpEffect : Effect
