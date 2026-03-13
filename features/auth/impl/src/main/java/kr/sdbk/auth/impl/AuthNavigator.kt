package kr.sdbk.auth.impl

import androidx.compose.runtime.remember
import kr.sdbk.auth.api.LoginRoute
import kr.sdbk.auth.api.SignUpRoute
import kr.sdbk.auth.api.navigateToSignUp
import kr.sdbk.auth.impl.login.LoginEvents
import kr.sdbk.auth.impl.login.LoginScreen
import kr.sdbk.auth.impl.signup.SignUpEvents
import kr.sdbk.auth.impl.signup.SignUpScreen
import kr.sdbk.main.api.navigateToHome
import kr.sdbk.navigation.OnAirEntryProviderScope
import kr.sdbk.navigation.OnAirNavigator

fun OnAirEntryProviderScope.authNavigator(
    navigator: OnAirNavigator
) {
    entry<LoginRoute> {
        val events = remember {
            LoginEvents(
                navigateToHome = navigator::navigateToHome,
                navigateToSignUp = navigator::navigateToSignUp
            )
        }
        LoginScreen(events)
    }
    entry<SignUpRoute> {
        val events = remember {
            SignUpEvents(
                onBackPressed = navigator::goBack
            )
        }
        SignUpScreen(events)
    }
}
