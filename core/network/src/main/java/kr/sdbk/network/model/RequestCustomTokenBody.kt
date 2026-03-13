package kr.sdbk.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class RequestCustomTokenBody(
    val accessToken: String,
    val socialType: String
)