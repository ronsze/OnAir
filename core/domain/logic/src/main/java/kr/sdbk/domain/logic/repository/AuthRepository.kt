package kr.sdbk.domain.logic.repository

import kr.sdbk.domain.model.user_auth.AuthToken
import kr.sdbk.domain.model.user_data.User

interface AuthRepository {
    fun getCurrentUser(): User?

    suspend fun login(): AuthToken

    suspend fun logout()
    suspend fun deleteAccount()
    suspend fun refresh(refreshToken: String): AuthToken
}