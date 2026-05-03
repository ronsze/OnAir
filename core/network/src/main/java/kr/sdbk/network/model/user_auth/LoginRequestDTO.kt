package kr.sdbk.network.model.user_auth

import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
@kotlinx.serialization.Serializable
data class LoginRequestDTO(
    val grantType: String,
    val clientId: String,
    val clientSecret: String,
    val code: String,
    val state: String
)