package kr.sdbk.network.datasource

import jakarta.inject.Inject
import kr.sdbk.data.datasource.network.AuthNetworkDataSource
import kr.sdbk.domain.model.user_auth.AuthToken
import kr.sdbk.domain.model.user_data.User
import kr.sdbk.network.BuildConfig
import kr.sdbk.network.api.AuthApi
import kr.sdbk.network.consts.APIResultCode
import kr.sdbk.network.di.ChzzkApi
import kr.sdbk.network.mapper.AuthMapper.toDomain
import kr.sdbk.network.model.user_auth.LoginRequestDTO
import retrofit2.Retrofit

internal class AuthNetworkDataSourceImpl @Inject constructor(
    @ChzzkApi chzzkRetrofit: Retrofit
) : AuthNetworkDataSource {
    companion object {
        private const val LOGIN_GRANT_TYPE = "authorization_code"
    }

    private val api = chzzkRetrofit.create(AuthApi::class.java)

    override suspend fun getCurrentUser(): User? = api.getCurrentUser().content.toDomain()

    override suspend fun login(code: String): AuthToken {
        val clientId = BuildConfig.chzzkClientId
        val state = BuildConfig.chzzkState

        val request = LoginRequestDTO(
            grantType = LOGIN_GRANT_TYPE,
            clientId = clientId,
            clientSecret = BuildConfig.chzzkClientSecret,
            code = code,
            state = state
        )

        return api.login(request).content.toDomain()
    }

    override suspend fun logout() {

    }

    override suspend fun deleteAccount() {

    }
}