package kr.sdbk.data.datasource.network

import kr.sdbk.domain.model.user_auth.AuthToken

interface AuthNetworkDataSource {
    suspend fun login(code: String): AuthToken
    suspend fun logout()
    suspend fun deleteAccount()
}