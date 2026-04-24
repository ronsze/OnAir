package kr.sdbk.network.data_source

import jakarta.inject.Inject
import kr.sdbk.data.data_source.LiveNetworkDataSource
import kr.sdbk.network.api.LiveAPI
import kr.sdbk.network.mapper.LiveMapper.toDomain
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit

class LiveNetworkDataSourceImpl @Inject constructor(
    retrofit: Retrofit
) : LiveNetworkDataSource {
    private val liveAPI: LiveAPI = retrofit.create(LiveAPI::class.java)

    override suspend fun getLives(size: Int?, next: String?) =
        liveAPI.getLives(size, next).map { it.toDomain() }

    override suspend fun getStreamKey(id: Int) =
        liveAPI.getStreamKey(id)
}
