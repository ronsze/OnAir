package kr.sdbk.onair

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kr.sdbk.navigation.OnAirNavHost
import kr.sdbk.onair.navigation.onAirEntryProvider
import kr.sdbk.onboarding.api.navigation.OnboardingNavRoute

/**
 * Main app entry point with Navigation 3 integration
 */
@Composable
internal fun OnAirApp(
    isOnboardingComplete: Boolean = false,
    modifier: Modifier = Modifier
) {
    OnAirNavHost(
        startRoute = OnboardingNavRoute.Welcome,
        modifier = modifier.fillMaxSize(),
        entryProvider = onAirEntryProvider(
            onOnboardingComplete = {
                // Navigate to Home when onboarding is complete
                // In a real app, this would also save the onboarding completion state
            }
        )
    )
}
