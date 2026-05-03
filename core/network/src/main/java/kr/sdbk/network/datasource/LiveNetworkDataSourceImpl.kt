package kr.sdbk.network.datasource

import jakarta.inject.Inject
import kr.sdbk.data.datasource.network.LiveNetworkDataSource
import kr.sdbk.network.BuildConfig
import kr.sdbk.network.api.LiveAPI
import kr.sdbk.network.di.ChzzkApi
import kr.sdbk.network.mapper.LiveMapper.toDomain
import retrofit2.Retrofit

class LiveNetworkDataSourceImpl @Inject constructor(
    @ChzzkApi retrofit: Retrofit
) : LiveNetworkDataSource {
    private val liveAPI: LiveAPI = retrofit.create(LiveAPI::class.java)

    override suspend fun getLives(size: Int?, next: String?) =
        liveAPI.getLives(
            clientId = BuildConfig.chzzkClientId,
            clientSecret = BuildConfig.chzzkClientSecret,
            size = size,
            next = next
        ).content.toDomain()

    override suspend fun getStreamKey(id: Int) =
        liveAPI.getStreamKey(id).content

    private fun getBuildConfigValue(
        key: String,
        fallbackKey: String
    ): String {
        return runCatching { BuildConfig::class.java.getField(key).get(null) as String }
            .recoverCatching { BuildConfig::class.java.getField(fallbackKey).get(null) as String }
            .getOrDefault("")
    }
}
