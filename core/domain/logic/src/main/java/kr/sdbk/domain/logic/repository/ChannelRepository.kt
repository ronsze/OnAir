package kr.sdbk.domain.logic.repository

import kr.sdbk.domain.model.Category

interface ChannelRepository {
    suspend fun getCategories(): List<Category>
}