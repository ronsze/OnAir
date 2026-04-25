package kr.sdbk.domain.model.live

import kotlinx.serialization.Serializable
import kr.sdbk.domain.model.Page

@Serializable
data class LivesResponse(
    val data: List<Live>,
    val page: Page
)