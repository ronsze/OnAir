package kr.sdbk.onboarding.impl.splash

import kr.sdbk.coordinator.viewmodel.Effect
import kr.sdbk.coordinator.viewmodel.Intent
import kr.sdbk.coordinator.viewmodel.State

internal data object SplashState : State

internal sealed interface SplashIntent : Intent {
    data class PermissionHandled(val isGranted: Boolean) : SplashIntent
}

internal sealed interface SplashEffect : Effect {
    data object NavigateToLogin : SplashEffect
    data object NavigateToHome : SplashEffect
}