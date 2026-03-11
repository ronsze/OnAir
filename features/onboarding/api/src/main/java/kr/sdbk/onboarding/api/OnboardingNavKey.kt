package kr.sdbk.onboarding.api

import kotlinx.serialization.Serializable
import kr.sdbk.navigation.OnAirNavKey
import kr.sdbk.navigation.OnAirNavigator

interface OnboardingNavKey : OnAirNavKey

fun OnAirNavigator.navigateToSplash() = navigate(SplashRoute)
fun OnAirNavigator.navigateToMaintenance() = navigate(MaintenanceRoute)

@Serializable data object SplashRoute : OnboardingNavKey
@Serializable data object MaintenanceRoute : OnboardingNavKey