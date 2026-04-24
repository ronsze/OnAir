package kr.sdbk.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val categoryType: String,
    val categoryId: String,
    val categoryValue: String,
    val posterImageUrl: String
)
