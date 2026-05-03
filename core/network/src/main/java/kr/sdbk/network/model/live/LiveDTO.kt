package kr.sdbk.network.model.live

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
data class LiveDTO(
    val liveId: Int,
    val liveTitle: String,
    val liveThumbnailImageUrl: String,
    val concurrentUserCount: Int,
    val openDate: String,
    val adult: Boolean,
    val tags: List<String> = emptyList(),
    val categoryType: String = "",
    val liveCategory: String = "",
    val liveCategoryValue: String = "",
    val channelId: String,
    val channelName: String,
    val channelImageUrl: String
)