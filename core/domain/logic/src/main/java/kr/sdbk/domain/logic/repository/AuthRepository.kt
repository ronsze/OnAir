package kr.sdbk.domain.logic.repository

import kr.sdbk.domain.model.SocialType
import kr.sdbk.domain.model.User

interface AuthRepository {
    fun getCurrentUser(): User?

    suspend fun loginWithEmail(email: String, password: String)
    suspend fun loginWithSocial(token: String, socialType: SocialType)

    suspend fun signUpWithEmail(email: String, password: String)

    suspend fun logout()
    suspend fun deleteAccount()
}