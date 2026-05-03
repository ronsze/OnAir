package kr.sdbk.network.datasource

import jakarta.inject.Inject
import kr.sdbk.data.datasource.network.TokenNetworkDataSource
import kr.sdbk.domain.model.user_auth.AuthToken
import kr.sdbk.network.BuildConfig
import kr.sdbk.network.api.AuthApi
import kr.sdbk.network.mapper.AuthMapper.toDomain
import kr.sdbk.network.model.user_auth.RefreshTokenRequestDTO
import retrofit2.Retrofit
import kr.sdbk.network.di.AuthApi as AuthApiQualifier

internal class TokenNetworkDataSourceImpl @Inject constructor(
    @AuthApiQualifier retrofit: Retrofit
) : TokenNetworkDataSource {
    companion object {
        private const val REFRESH_GRANT_TYPE = "refresh_token"
    }

    private val authApi = retrofit.create(AuthApi::class.java)

    override suspend fun refresh(refreshToken: String): AuthToken {
        val request = RefreshTokenRequestDTO(
            grantType = REFRESH_GRANT_TYPE,
            refreshToken = refreshToken,
            clientId = BuildConfig.chzzkClientId,
            clientSecret = BuildConfig.chzzkClientSecret
        )

        return authApi.refresh(request).content.toDomain()
    }
}
