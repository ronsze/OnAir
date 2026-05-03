package kr.sdbk.network.datasource

import jakarta.inject.Inject
import kr.sdbk.data.datasource.network.ChannelNetworkDataSource
import kr.sdbk.network.api.ChannelAPI
import kr.sdbk.network.di.ChzzkApi
import kr.sdbk.network.mapper.ChannelMapper.toDomain
import retrofit2.Retrofit

class ChannelNetworkDataSourceImpl @Inject constructor(
    @ChzzkApi retrofit: Retrofit
) : ChannelNetworkDataSource {
    private val channelAPI: ChannelAPI = retrofit.create(ChannelAPI::class.java)

    override suspend fun getCategories() =
        channelAPI.getCategories().map { it.toDomain() }
}
