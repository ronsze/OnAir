package kr.sdbk.auth.api

import kotlinx.serialization.Serializable
import kr.sdbk.navigation.OnAirNavKey
import kr.sdbk.navigation.OnAirNavigator

interface AuthNavKey : OnAirNavKey

fun OnAirNavigator.navigateToLogin() = navigate(LoginRoute)
fun OnAirNavigator.navigateToSignUp() = navigate(SignUpRoute)

@Serializable
data object LoginRoute : AuthNavKey

@Serializable
data object SignUpRoute : AuthNavKey
