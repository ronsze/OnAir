package kr.sdbk.data.repository

import jakarta.inject.Inject
import kr.sdbk.data.datasource.network.ChannelNetworkDataSource
import kr.sdbk.domain.logic.repository.ChannelRepository
import kr.sdbk.domain.model.channel.Category

class ChannelRepositoryImpl @Inject constructor(
    private val networkDataSource: ChannelNetworkDataSource
) : ChannelRepository {
    override suspend fun getCategories(): List<Category> =
        networkDataSource.getCategories()
}