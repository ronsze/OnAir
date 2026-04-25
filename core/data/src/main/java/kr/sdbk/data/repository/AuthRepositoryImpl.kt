package kr.sdbk.data.repository

import jakarta.inject.Inject
import kr.sdbk.data.datasource.network.AuthNetworkDataSource
import kr.sdbk.domain.logic.repository.AuthRepository
import kr.sdbk.domain.model.user_auth.SocialType
import kr.sdbk.domain.model.user_data.User

internal class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: AuthNetworkDataSource
) : AuthRepository {
    override fun getCurrentUser(): User? = null

    override suspend fun login() =
        networkDataSource.login()

    override suspend fun logout() =
        networkDataSource.logout()

    override suspend fun deleteAccount() =
        networkDataSource.deleteAccount()

    override suspend fun refresh(refreshToken: String) =
        networkDataSource.refresh(refreshToken)
}