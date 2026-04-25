package kr.sdbk.network.model.user_auth

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenRequestDTO(
    val grant_type: String,
    val refresh_token: String,
    val client_id: String,
    val client_secret: String
)