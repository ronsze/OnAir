package kr.sdbk.data.datasource.network

import kr.sdbk.domain.model.user_auth.AuthToken
import kr.sdbk.domain.model.user_data.User

interface AuthNetworkDataSource {
    suspend fun getCurrentUser(): User?
    suspend fun login(code: String): AuthToken
    suspend fun logout()
    suspend fun deleteAccount()
}