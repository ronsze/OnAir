package kr.sdbk.network.model.user_auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthCodeResponseDTO(
    val code: String,
    val state: String
)