package kr.sdbk.data.datasource.local

import kr.sdbk.domain.model.user_auth.AuthToken

interface TokenLocalDataSource {
    suspend fun setToken(token: AuthToken)
    suspend fun getToken(): AuthToken?

    suspend fun clearToken()
}