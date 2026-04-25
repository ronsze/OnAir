package kr.sdbk.network.api

import kr.sdbk.network.model.live.LivesResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface LiveAPI {
    @GET("/open/v1/lives")
    suspend fun getLives(
        @Query("size") size: Int?,
        @Query("next") next: String?
    ): LivesResponseDTO

    @GET("/open/v1/streams/key")
    suspend fun getStreamKey(
        @Query("id"
        ) id: Int): String
}
