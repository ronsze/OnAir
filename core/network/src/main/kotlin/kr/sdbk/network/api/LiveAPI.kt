package kr.sdbk.network.api

import kr.sdbk.network.model.LiveDTO
import retrofit2.http.GET

interface LiveAPI {
    @GET("/open/v1/lives")
    suspend fun getLives(size: Int?, next: String?): List<LiveDTO>

    @GET("/open/v1/streams/key")
    suspend fun getStreamKey(id: Int): String
}
