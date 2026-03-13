package kr.sdbk.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class CustomTokenResponse(
    val token: String
)