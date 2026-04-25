package kr.sdbk.network.model.user_auth

data class LoginRequestDTO(
    val grant_type: String,
    val client_id: String,
    val client_secret: String,
    val code: String,
    val state: String
)