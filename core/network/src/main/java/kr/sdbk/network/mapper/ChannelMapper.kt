package kr.sdbk.network.mapper

import kr.sdbk.domain.model.channel.Category
import kr.sdbk.network.model.channel.CategoryDTO

object ChannelMapper {
    fun CategoryDTO.toDomain() = Category(
        categoryType = category_type,
        categoryId = category_id,
        categoryValue = category_value,
        posterImageUrl = poster_image_url,
    )
}