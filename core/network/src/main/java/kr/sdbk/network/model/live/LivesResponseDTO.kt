package kr.sdbk.network.model.live

import kotlinx.serialization.Serializable
import kr.sdbk.domain.model.Page

@Serializable
data class LivesResponseDTO(
    val data: List<LiveDTO>,
    val page: Page
)