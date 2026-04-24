package kr.sdbk.main.impl

import androidx.compose.runtime.remember
import kr.sdbk.main.api.TopLevelDestinations
import kr.sdbk.main.impl.following.FollowingEvents
import kr.sdbk.main.impl.following.FollowingScreen
import kr.sdbk.main.impl.home.HomeEvents
import kr.sdbk.main.impl.home.HomeScreen
import kr.sdbk.main.impl.lives.LiveEvents
import kr.sdbk.main.impl.lives.LiveScreen
import kr.sdbk.main.impl.my.MyEvents
import kr.sdbk.main.impl.my.MyScreen
import kr.sdbk.navigation.OnAirEntryProviderScope
import kr.sdbk.navigation.OnAirNavigator

fun OnAirEntryProviderScope.mainNavigator(navigator: OnAirNavigator) {
    entry<TopLevelDestinations.HomeNavKey> {
        val events = remember { HomeEvents }
        HomeScreen(events)
    }

    entry<TopLevelDestinations.FollowingNavKey> {
        val events = remember { FollowingEvents }
        FollowingScreen(events)
    }

    entry<TopLevelDestinations.LivesNavKey> {
        val events = remember { LiveEvents }
        LiveScreen(events)
    }

    entry<TopLevelDestinations.MyNavKey> {
        val events = remember { MyEvents }
        MyScreen(events)
    }
}
