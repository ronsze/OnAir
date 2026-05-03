package kr.sdbk.network.api

import kr.sdbk.network.model.BaseResponse
import kr.sdbk.network.model.user_auth.AuthTokenDTO
import kr.sdbk.network.model.user_auth.LoginRequestDTO
import kr.sdbk.network.model.user_auth.RefreshTokenRequestDTO
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthApi {
    @POST("/auth/v1/token")
    suspend fun login(
        @Body requestBody: LoginRequestDTO
    ): BaseResponse<AuthTokenDTO>

    @POST("/auth/v1/token")
    suspend fun refresh(
        @Body requestBody: RefreshTokenRequestDTO
    ): BaseResponse<AuthTokenDTO>
}