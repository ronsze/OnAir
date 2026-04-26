package kr.sdbk.auth.impl

import androidx.compose.runtime.remember
import kr.sdbk.auth.api.LoginRoute
import kr.sdbk.auth.impl.login.LoginEvents
import kr.sdbk.auth.impl.login.LoginScreen
import kr.sdbk.main.api.navigateToHome
import kr.sdbk.navigation.OnAirEntryProviderScope
import kr.sdbk.navigation.OnAirNavigator

fun OnAirEntryProviderScope.authNavigator(
    navigator: OnAirNavigator
) {
    entry<LoginRoute> {
        val events = remember {
            LoginEvents(
                navigateToHome = navigator::navigateToHome
            )
        }
        LoginScreen(events)
    }
}
