package kr.sdbk.data.repository

import jakarta.inject.Inject
import kr.sdbk.data.data_source.ChannelNetworkDataSource
import kr.sdbk.domain.logic.repository.ChannelRepository
import kr.sdbk.domain.model.Category

class ChannelRepositoryImpl @Inject constructor(
    private val networkDataSource: ChannelNetworkDataSource
) : ChannelRepository {
    override suspend fun getCategories(): List<Category> =
        networkDataSource.getCategories()
}