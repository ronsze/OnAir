package kr.sdbk.data.repository

import jakarta.inject.Inject
import kr.sdbk.data.datasource.local.TokenLocalDataSource
import kr.sdbk.data.datasource.network.TokenNetworkDataSource
import kr.sdbk.domain.logic.repository.TokenRepository
import kr.sdbk.domain.model.user_auth.AuthToken

class TokenRepositoryImpl @Inject constructor(
    private val localDataSource: TokenLocalDataSource,
    private val tokenNetworkDataSource: TokenNetworkDataSource
): TokenRepository {
    override suspend fun setToken(token: AuthToken) =
        localDataSource.setToken(token)

    override suspend fun getToken(): AuthToken? =
        localDataSource.getToken()

    override suspend fun refresh(refreshToken: String): AuthToken =
        tokenNetworkDataSource.refresh(refreshToken)

    override suspend fun clearToken() =
        localDataSource.clearToken()
}