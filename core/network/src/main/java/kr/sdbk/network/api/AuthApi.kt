package kr.sdbk.network.api

import kr.sdbk.network.model.user_auth.AuthCodeResponseDTO
import kr.sdbk.network.model.user_auth.AuthTokenDTO
import kr.sdbk.network.model.user_auth.LoginRequestDTO
import kr.sdbk.network.model.user_auth.RefreshTokenRequestDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface AuthApi {
    @GET("/account-interlock")
    suspend fun getAuthCode(
        @Query("client_id") clientId: String,
        @Query("redirect_uri") redirectUri: String,
        @Query("state") state: String,
    ): AuthCodeResponseDTO

    @POST("/auth/v1/token")
    suspend fun login(
        @Body requestBody: LoginRequestDTO
    ): AuthTokenDTO

    @POST("/auth/v1/token")
    suspend fun refresh(
        @Body requestBody: RefreshTokenRequestDTO
    ): AuthTokenDTO
}