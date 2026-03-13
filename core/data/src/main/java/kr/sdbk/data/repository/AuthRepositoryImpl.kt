package kr.sdbk.data.repository

import jakarta.inject.Inject
import kr.sdbk.data.datasource.network.AuthNetworkDataSource
import kr.sdbk.domain.logic.repository.AuthRepository
import kr.sdbk.domain.model.SocialType
import kr.sdbk.domain.model.User

internal class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: AuthNetworkDataSource
) : AuthRepository {
    override fun getCurrentUser(): User? = networkDataSource.getCurrentUser()

    override suspend fun loginWithEmail(email: String, password: String) =
        networkDataSource.loginWithEmail(email, password)

    override suspend fun loginWithSocial(token: String, socialType: SocialType) =
        networkDataSource.loginWithSocial(token, socialType)

    override suspend fun signUpWithEmail(email: String, password: String) =
        networkDataSource.signUpWithEmail(email, password)

    override suspend fun logout() =
        networkDataSource.logout()

    override suspend fun deleteAccount() =
        networkDataSource.deleteAccount()
}