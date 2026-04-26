package kr.sdbk.network.model.user_auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthTokenDTO(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Long,
    val tokenType: String
)