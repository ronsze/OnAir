package kr.sdbk.network.mapper

import kr.sdbk.domain.model.channel.Category
import kr.sdbk.network.model.channel.CategoryDTO

object ChannelMapper {
    fun CategoryDTO.toDomain() = Category(
        categoryType = categoryType,
        categoryId = categoryId,
        categoryValue = categoryValue,
        posterImageUrl = posterImageUrl,
    )
}