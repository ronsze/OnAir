package kr.sdbk.network.datasource

import jakarta.inject.Inject
import kr.sdbk.data.datasource.network.AuthNetworkDataSource
import kr.sdbk.domain.model.user_auth.AuthToken
import kr.sdbk.network.api.AuthApi
import kr.sdbk.network.di.ChzzkApi
import kr.sdbk.network.mapper.AuthMapper.toDomain
import kr.sdbk.network.model.user_auth.LoginRequestDTO
import kr.sdbk.network.model.user_auth.RefreshTokenRequestDTO
import retrofit2.Retrofit

internal class AuthNetworkDataSourceImpl @Inject constructor(
    @ChzzkApi retrofit: Retrofit
) : AuthNetworkDataSource {
    companion object {
        private const val GRANT_TYPE = "authorization_code"
    }

    private val api = retrofit.create(AuthApi::class.java)

    override suspend fun login(): AuthToken {
        val clientId = "BuildConfig.CHZZK_CLIENT_ID"
        val redirectUri = "BuildConfig.CHZZK_REDIRECT_URI"
        val state = "BuildConfig.CHZZK_STATE"

        val authCode = getAuthCode(
            clientId = clientId,
            redirectUri = redirectUri,
            state = state
        )

        val request = LoginRequestDTO(
            grant_type = GRANT_TYPE,
            client_id = clientId,
            client_secret = "clientSecret",
            code = authCode,
            state = state
        )

        return api.login(request).toDomain()
    }

    override suspend fun refresh(refreshToken: String): AuthToken {
        val request = RefreshTokenRequestDTO(
            grant_type = "refresh_token",
            refresh_token = refreshToken,
            client_id = "BuildConfig.CHZZK_CLIENT_ID",
            client_secret = "BuildConfig.CHZZK_CLIENT_SECRET"
        )

        return api.refresh(request).toDomain()
    }

    private suspend fun getAuthCode(
        clientId: String,
        redirectUri: String,
        state: String,
    ): String {
        val res = api.getAuthCode(clientId, redirectUri, state)
        if (res.state != state) throw IllegalArgumentException("State tampered")
        return res.code
    }

    override suspend fun logout() {

    }

    override suspend fun deleteAccount() {

    }
}