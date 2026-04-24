package kr.sdbk.network.data_source

import jakarta.inject.Inject
import kr.sdbk.data.data_source.ChannelNetworkDataSource
import kr.sdbk.network.api.ChannelAPI
import kr.sdbk.network.mapper.ChannelMapper.toDomain
import retrofit2.Retrofit

class ChannelNetworkDataSourceImpl @Inject constructor(
    retrofit: Retrofit
) : ChannelNetworkDataSource {
    private val channelAPI: ChannelAPI = retrofit.create(ChannelAPI::class.java)

    override suspend fun getCategories() =
        channelAPI.getCategories().map { it.toDomain() }
}
