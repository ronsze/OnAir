package kr.sdbk.auth.impl.signup

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kr.sdbk.coordinator.viewmodel.BaseViewModel

@HiltViewModel
internal class SignUpViewModel @Inject constructor() : BaseViewModel<SignUpState, SignUpIntent, SignUpEffect>(
    initialState = SignUpState()
) {
    override fun handleIntent(intent: SignUpIntent) {
        when (intent) {
            is SignUpIntent.InputEmail -> onInputEmail(intent.email)
            is SignUpIntent.InputPassword -> onInputPassword(intent.password)
            is SignUpIntent.InputConfirmPassword -> onInputConfirmPassword(intent.confirmPassword)
            SignUpIntent.ClickSignUp -> onClickSignUp()
        }
    }

    private fun onInputEmail(email: String) {
        updateState { copy(email = email) }
    }

    private fun onInputPassword(password: String) {
        updateState { copy(password = password) }
    }

    private fun onInputConfirmPassword(confirmPassword: String) {
        updateState { copy(confirmPassword = confirmPassword) }
    }

    private fun onClickSignUp() {

    }
}
