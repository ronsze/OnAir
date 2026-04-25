package kr.sdbk.domain.logic.repository

import kr.sdbk.domain.model.live.LivesResponse

interface LiveRepository {
    suspend fun getLives(size: Int?, next: String?): LivesResponse
    suspend fun getStreamKey(id: Int): String
}