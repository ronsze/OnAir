package kr.sdbk.data.datasource.network

import kr.sdbk.domain.model.user_auth.AuthToken

interface AuthNetworkDataSource {
    suspend fun login(): AuthToken
    suspend fun logout()
    suspend fun deleteAccount()
    suspend fun refresh(refreshToken: String): AuthToken
}