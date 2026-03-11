package kr.sdbk.onboarding.api

import kr.sdbk.navigation.OnAirNavKey
import kr.sdbk.navigation.OnAirNavigator

interface OnboardingNavKey : OnAirNavKey

fun OnAirNavigator.navigateToSplash() = navigate(SplashRoute)

data object SplashRoute : OnboardingNavKey