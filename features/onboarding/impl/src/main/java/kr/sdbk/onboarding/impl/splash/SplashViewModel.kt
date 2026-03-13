package kr.sdbk.onboarding.impl.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kr.sdbk.coordinator.viewmodel.BaseViewModel
import kr.sdbk.domain.logic.usecase.auth.GetUserUseCase

@HiltViewModel
internal class SplashViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel<SplashState, SplashIntent, SplashEffect>(
    initialState = SplashState
) {
    override fun handleIntent(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.PermissionHandled -> onPermissionHandled(intent.isGranted)
        }
    }

    private fun onPermissionHandled(isGranted: Boolean) {
        if (isGranted) {
            checkMaintenance()
        } else {
            //  TODO
        }
    }

    private fun checkMaintenance() {
        checkVersion()
    }

    private fun checkVersion() {
        checkUser()
    }

    private fun checkUser() {
        val currentUser = getUserUseCase()
        val effect = if (currentUser != null) SplashEffect.NavigateToHome else SplashEffect.NavigateToLogin
        sendEffect(effect)
    }

}