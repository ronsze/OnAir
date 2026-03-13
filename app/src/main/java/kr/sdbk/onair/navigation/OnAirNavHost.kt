package kr.sdbk.onair.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import kr.sdbk.auth.impl.authNavigator
import kr.sdbk.main.impl.mainNavigator
import kr.sdbk.navigation.OnAirNavigator
import kr.sdbk.onboarding.impl.onboardingNavigator
import kr.sdbk.support.impl.supportNavigator

@Composable
internal fun OnAirNavHost(
    navigator: OnAirNavigator,
    modifier: Modifier = Modifier
) {
    NavDisplay(
        backStack = navigator.navigationState.backStack,
        onBack = navigator::goBack,
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            onboardingNavigator(navigator)
            mainNavigator(navigator)
            supportNavigator(navigator)
            authNavigator(navigator)
        }
    )
}