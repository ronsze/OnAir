package kr.sdbk.network.model.live

import kotlinx.serialization.Serializable

@Serializable
data class LiveDTO(
    val live_id: Int,
    val live_title: String,
    val live_thumbnail_image_url: String,
    val concurrent_user_count: Int,
    val open_date: String,
    val adult: Boolean,
    val tags: List<String>,
    val category_type: String,
    val live_category: String,
    val live_category_value: String,
    val channel_id: String,
    val channel_name: String,
    val channel_image_url: String
)