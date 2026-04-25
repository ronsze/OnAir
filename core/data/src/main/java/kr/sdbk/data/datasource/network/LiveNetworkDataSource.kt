package kr.sdbk.data.datasource.network

import kr.sdbk.domain.model.live.LivesResponse

interface LiveNetworkDataSource {
    suspend fun getLives(size: Int?, next: String?): LivesResponse
    suspend fun getStreamKey(id: Int): String
}
