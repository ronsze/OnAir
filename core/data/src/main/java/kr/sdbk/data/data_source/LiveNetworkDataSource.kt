package kr.sdbk.data.data_source

import kr.sdbk.domain.model.Live

interface LiveNetworkDataSource {
    suspend fun getLives(size: Int?, next: String?): List<Live>
    suspend fun getStreamKey(id: Int): String
}
