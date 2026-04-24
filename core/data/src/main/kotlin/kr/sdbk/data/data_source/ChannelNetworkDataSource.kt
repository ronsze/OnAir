package kr.sdbk.data.data_source

import kr.sdbk.domain.model.Category

interface ChannelNetworkDataSource {
    suspend fun getCategories(): List<Category>
}
