package kr.sdbk.domain.logic.repository

import kr.sdbk.domain.model.user_auth.AuthToken

interface TokenRepository {
    suspend fun setToken(token: AuthToken)
    suspend fun getToken(): AuthToken?

    suspend fun clearToken()
}