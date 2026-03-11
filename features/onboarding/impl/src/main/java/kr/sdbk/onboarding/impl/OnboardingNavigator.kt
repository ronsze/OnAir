package kr.sdbk.onboarding.impl

import androidx.compose.runtime.remember
import kr.sdbk.navigation.OnAirEntryProviderScope
import kr.sdbk.navigation.OnAirNavKey
import kr.sdbk.navigation.OnAirNavigator
import kr.sdbk.onboarding.impl.splash.SplashEvents
import kr.sdbk.onboarding.impl.splash.SplashScreen

fun OnAirEntryProviderScope.onboardingNavigator(
    navigator: OnAirNavigator
) {
    entry<OnAirNavKey> {
        val events = remember {
            SplashEvents(
                navigateToHome = {}
            )
        }
        SplashScreen(events)
    }
}