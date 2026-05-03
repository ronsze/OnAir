package kr.sdbk.domain.logic.repository

import kr.sdbk.domain.model.user_auth.AuthToken
import kr.sdbk.domain.model.user_data.User

interface AuthRepository {
    suspend fun getCurrentUser(forceUpdate: Boolean): User?

    suspend fun login(code: String): AuthToken

    suspend fun logout()
    suspend fun deleteAccount()
}