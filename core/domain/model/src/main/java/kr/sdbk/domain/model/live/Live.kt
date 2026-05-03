package kr.sdbk.domain.model.live

import kotlinx.serialization.Serializable

@Serializable
data class Live(
    val liveId: Int,
    val liveTitle: String,
    val liveThumbnailImageUrl: String,
    val concurrentUserCount: Int,
    val openDate: String,
    val adult: Boolean,
    val tags: List<String>,
    val categoryType: String,
    val liveCategory: String,
    val liveCategoryValue: String,
    val channelId: String,
    val channelName: String,
    val channelImageUrl: String
) {
    companion object {
        val placeholder = Live(
            liveId = 0,
            liveTitle = "",
            liveThumbnailImageUrl = "",
            concurrentUserCount = 0,
            openDate = "",
            adult = false,
            tags = emptyList(),
            categoryType = "",
            liveCategory = "",
            liveCategoryValue = "",
            channelId = "",
            channelName = "",
            channelImageUrl = ""
        )
    }
}