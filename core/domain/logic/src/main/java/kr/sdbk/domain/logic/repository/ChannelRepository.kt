package kr.sdbk.domain.logic.repository

import kr.sdbk.domain.model.channel.Category

interface ChannelRepository {
    suspend fun getCategories(): List<Category>
}