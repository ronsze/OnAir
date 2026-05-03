package kr.sdbk.onboarding.impl.splash

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import kr.sdbk.coordinator.viewmodel.BaseViewModel
import kr.sdbk.domain.logic.usecase.user_auth.GetUserUseCase

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

        }
    }

    private fun checkMaintenance() {
        checkVersion()
    }

    private fun checkVersion() {
        checkUser()
    }

    private fun checkUser() {
        viewModelScope.launch {
            runTask(withLoading = false) {
                getUserUseCase(forceUpdate = true)
            }.onSuccess { user ->
                if (user == null) {
                    sendEffect(SplashEffect.NavigateToLogin)
                } else {
                    sendEffect(SplashEffect.NavigateToHome)
                }
            }.onBasicFailure {}
        }
    }
}