package kr.sdbk.network.mapper

import kr.sdbk.domain.model.live.Live
import kr.sdbk.domain.model.live.LivesResponse
import kr.sdbk.network.model.live.LiveDTO
import kr.sdbk.network.model.live.LivesResponseDTO

object LiveMapper {
    fun LivesResponseDTO.toDomain() = LivesResponse(
        data = data.map { it.toDomain() },
        page = page
    )

    fun LiveDTO.toDomain() = Live(
        liveId = liveId,
        liveTitle = liveTitle,
        liveThumbnailImageUrl = liveThumbnailImageUrl,
        concurrentUserCount = concurrentUserCount,
        openDate = openDate,
        adult = adult,
        tags = tags,
        categoryType = categoryType,
        liveCategory = liveCategory,
        liveCategoryValue = liveCategoryValue,
        channelId = channelId,
        channelName = channelName,
        channelImageUrl = channelImageUrl
    )
}