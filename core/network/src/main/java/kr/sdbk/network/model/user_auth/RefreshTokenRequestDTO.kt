package kr.sdbk.network.model.user_auth

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenRequestDTO(
    val grantType: String,
    val refreshToken: String,
    val clientId: String,
    val clientSecret: String
)