package kr.sdbk.support.api

import kotlinx.serialization.Serializable
import kr.sdbk.navigation.OnAirNavKey
import kr.sdbk.navigation.OnAirNavigator

interface SupportNavKey : OnAirNavKey

fun OnAirNavigator.navigateToSettings() = navigate(SettingsRoute)

@Serializable
data object SettingsRoute : SupportNavKey
