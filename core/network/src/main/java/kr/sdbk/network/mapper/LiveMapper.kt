package kr.sdbk.network.mapper

import kr.sdbk.domain.model.Live
import kr.sdbk.network.model.LiveDTO

object LiveMapper {
    fun LiveDTO.toDomain() = Live(
        liveId = live_id,
        liveTitle = live_title,
        liveThumbnailImageUrl = live_thumbnail_image_url,
        concurrentUserCount = concurrent_user_count,
        openDate = open_date,
        adult = adult,
        tags = tags,
        categoryType = category_type,
        liveCategory = live_category,
        liveCategoryValue = live_category_value,
        channelId = channel_id,
        channelName = channel_name,
        channelImageUrl = channel_image_url
    )
}