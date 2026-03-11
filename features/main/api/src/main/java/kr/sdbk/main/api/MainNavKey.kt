package kr.sdbk.main.api

import kotlinx.serialization.Serializable
import kr.sdbk.navigation.OnAirNavKey
import kr.sdbk.navigation.OnAirNavigator

interface MainNavKey : OnAirNavKey

fun OnAirNavigator.navigateToHome() = navigate(HomeRoute)

@Serializable data object HomeRoute : MainNavKey
