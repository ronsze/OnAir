package kr.sdbk.domain.logic.repository

import kr.sdbk.domain.model.Live

interface LiveRepository {
    suspend fun getLives(size: Int?, next: String?): List<Live>
    suspend fun getStreamKey(id: Int): String
}