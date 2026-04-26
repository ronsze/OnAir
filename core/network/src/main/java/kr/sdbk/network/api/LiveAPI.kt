package kr.sdbk.network.api

import kr.sdbk.network.consts.APIHeader
import kr.sdbk.network.model.BaseResponse
import kr.sdbk.network.model.live.LivesResponseDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface LiveAPI {
    @GET("/open/v1/lives")
    suspend fun getLives(
        @Header(APIHeader.CLIENT_ID) clientId: String,
        @Header(APIHeader.CLIENT_SECRET) clientSecret: String,
        @Query("size") size: Int?,
        @Query("next") next: String?
    ): BaseResponse<LivesResponseDTO>

    @GET("/open/v1/streams/key")
    suspend fun getStreamKey(
        @Query("id") id: Int
    ): BaseResponse<String>
}
