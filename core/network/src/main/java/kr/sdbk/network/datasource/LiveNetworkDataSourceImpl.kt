package kr.sdbk.network.datasource

import jakarta.inject.Inject
import kr.sdbk.data.datasource.network.LiveNetworkDataSource
import kr.sdbk.network.api.LiveAPI
import kr.sdbk.network.di.ChzzkApi
import kr.sdbk.network.mapper.LiveMapper.toDomain
import retrofit2.Retrofit

class LiveNetworkDataSourceImpl @Inject constructor(
    @ChzzkApi retrofit: Retrofit
) : LiveNetworkDataSource {
    private val liveAPI: LiveAPI = retrofit.create(LiveAPI::class.java)

    override suspend fun getLives(size: Int?, next: String?) =
        liveAPI.getLives(size, next).toDomain()

    override suspend fun getStreamKey(id: Int) =
        liveAPI.getStreamKey(id)
}
