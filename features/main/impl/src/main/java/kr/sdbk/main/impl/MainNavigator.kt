package kr.sdbk.main.impl

import androidx.compose.runtime.remember
import kr.sdbk.main.api.HomeRoute
import kr.sdbk.navigation.OnAirEntryProviderScope
import kr.sdbk.navigation.OnAirNavigator
import kr.sdbk.main.impl.home.HomeEvents
import kr.sdbk.main.impl.home.HomeScreen

fun OnAirEntryProviderScope.mainNavigator(navigator: OnAirNavigator) {
    entry<HomeRoute> {
        val events = remember { HomeEvents }
        HomeScreen(events)
    }
}
