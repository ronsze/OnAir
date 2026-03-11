package kr.sdbk.support.impl

import androidx.compose.runtime.remember
import kr.sdbk.navigation.OnAirEntryProviderScope
import kr.sdbk.navigation.OnAirNavigator
import kr.sdbk.support.api.SettingsRoute
import kr.sdbk.support.impl.setting.SettingsEvents
import kr.sdbk.support.impl.setting.SettingsScreen

fun OnAirEntryProviderScope.supportNavigator(
    navigator: OnAirNavigator
) {
    entry<SettingsRoute> {
        val events = remember {
            SettingsEvents(
                goBack = navigator::goBack
            )
        }
        SettingsScreen(events)
    }
}
