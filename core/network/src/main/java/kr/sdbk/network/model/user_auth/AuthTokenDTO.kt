package kr.sdbk.network.model.user_auth

import kotlinx.serialization.Serializable

@Serializable
class AuthTokenDTO(
    val access_token: String,
    val refresh_token: String,
    val expires_in: Long,
    val token_type: String
)