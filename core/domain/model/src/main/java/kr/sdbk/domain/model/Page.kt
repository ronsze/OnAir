package kr.sdbk.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Page(
    val next: String
)