package kr.sdbk.onboarding.impl.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kr.sdbk.coordinator.viewmodel.BaseViewModel

@HiltViewModel
internal class SplashViewModel @Inject constructor(

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
        sendEffect(SplashEffect.NavigateToLogin)
    }
}