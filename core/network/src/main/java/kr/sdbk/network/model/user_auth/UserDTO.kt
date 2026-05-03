package kr.sdbk.network.model.user_auth

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val channelId: String,
    val channelName: String
)