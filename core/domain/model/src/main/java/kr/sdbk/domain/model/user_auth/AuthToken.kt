package kr.sdbk.domain.model.user_auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthToken(
    val accessToken: String,
    val refreshToken: String,
    val expireAt: Long,
    val tokenType: String
)