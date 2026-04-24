package kr.sdbk.network.api

import kr.sdbk.network.model.CustomTokenResponse
import kr.sdbk.network.model.RequestCustomTokenBody
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthApi {
    @POST("getCustomToken")
    suspend fun getFirebaseCustomToken(
        @Body requestBody: RequestCustomTokenBody
    ): CustomTokenResponse
}