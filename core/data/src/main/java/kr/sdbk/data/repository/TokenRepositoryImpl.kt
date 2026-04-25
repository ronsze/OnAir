package kr.sdbk.data.repository

import jakarta.inject.Inject
import kr.sdbk.data.datasource.local.TokenLocalDataSource
import kr.sdbk.domain.logic.repository.TokenRepository
import kr.sdbk.domain.model.user_auth.AuthToken

class TokenRepositoryImpl @Inject constructor(
    private val localDataSource: TokenLocalDataSource
): TokenRepository {
    override suspend fun setToken(token: AuthToken) =
        localDataSource.setToken(token)

    override suspend fun getToken(): AuthToken? =
        localDataSource.getToken()

    override suspend fun clearToken() =
        localDataSource.clearToken()
}