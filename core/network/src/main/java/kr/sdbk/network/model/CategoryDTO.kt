package kr.sdbk.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    val category_type: String,
    val category_id: String,
    val category_value: String,
    val poster_image_url: String
)
