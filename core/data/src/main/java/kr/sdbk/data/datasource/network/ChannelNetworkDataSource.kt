package kr.sdbk.data.datasource.network

import kr.sdbk.domain.model.channel.Category

interface ChannelNetworkDataSource {
    suspend fun getCategories(): List<Category>
}
