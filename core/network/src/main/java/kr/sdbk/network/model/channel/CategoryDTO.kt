package kr.sdbk.network.model.channel

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    val categoryType: String,
    val categoryId: String,
    val categoryValue: String,
    val posterImageUrl: String
)