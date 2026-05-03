package kr.sdbk.network.api

import kr.sdbk.network.consts.APIHeader.ACCESS_TOKEN_HEADER
import kr.sdbk.network.model.BaseResponse
import kr.sdbk.network.model.user_auth.AuthTokenDTO
import kr.sdbk.network.model.user_auth.LoginRequestDTO
import kr.sdbk.network.model.user_auth.RefreshTokenRequestDTO
import kr.sdbk.network.model.user_auth.UserDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

internal interface AuthApi {
    @GET("/open/v1/users/me")
    @Headers(ACCESS_TOKEN_HEADER)
    suspend fun getCurrentUser(): BaseResponse<UserDTO>

    @POST("/auth/v1/token")
    suspend fun login(
        @Body requestBody: LoginRequestDTO
    ): BaseResponse<AuthTokenDTO>

    @POST("/auth/v1/token")
    suspend fun refresh(
        @Body requestBody: RefreshTokenRequestDTO
    ): BaseResponse<AuthTokenDTO>
}