package kr.sdbk.onboarding.impl

import androidx.compose.runtime.remember
import kr.sdbk.auth.api.navigateToLogin
import kr.sdbk.navigation.OnAirEntryProviderScope
import kr.sdbk.navigation.OnAirNavigator
import kr.sdbk.onboarding.api.MaintenanceRoute
import kr.sdbk.onboarding.api.SplashRoute
import kr.sdbk.onboarding.impl.maintenance.MaintenanceEvents
import kr.sdbk.onboarding.impl.maintenance.MaintenanceScreen
import kr.sdbk.onboarding.impl.splash.SplashEvents
import kr.sdbk.onboarding.impl.splash.SplashScreen

fun OnAirEntryProviderScope.onboardingNavigator(
    navigator: OnAirNavigator
) {
    entry<SplashRoute> {
        val events = remember {
            SplashEvents(
                navigateToLogin = navigator::navigateToLogin
            )
        }
        SplashScreen(events)
    }

    entry<MaintenanceRoute> {
        val events = remember {
            MaintenanceEvents(
                goBack = navigator::goBack
            )
        }
        MaintenanceScreen(events)
    }
}