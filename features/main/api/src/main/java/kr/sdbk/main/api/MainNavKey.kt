package kr.sdbk.main.api

import kotlinx.serialization.Serializable
import kr.sdbk.navigation.OnAirNavKey
import kr.sdbk.navigation.OnAirNavigator

interface MainNavKey : OnAirNavKey

fun OnAirNavigator.navigateToHome() =
    navigate(TopLevelDestinations.HomeNavKey)

fun OnAirNavigator.navigateToFollowing() =
    navigate(TopLevelDestinations.FollowingNavKey)

fun OnAirNavigator.navigateToLives() =
    navigate(TopLevelDestinations.LivesNavKey)

sealed interface TopLevelDestinations : MainNavKey {
    @Serializable data object HomeNavKey : TopLevelDestinations
    @Serializable data object FollowingNavKey : TopLevelDestinations
    @Serializable data object LivesNavKey : TopLevelDestinations
    @Serializable data object MyNavKey : TopLevelDestinations

    companion object {
        val entries get() = listOf(
            HomeNavKey,
            FollowingNavKey,
            LivesNavKey,
            MyNavKey
        )
    }
}