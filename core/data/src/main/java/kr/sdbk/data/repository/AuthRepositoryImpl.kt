package kr.sdbk.data.repository

import jakarta.inject.Inject
import kr.sdbk.data.datasource.network.AuthNetworkDataSource
import kr.sdbk.domain.logic.repository.AuthRepository
import kr.sdbk.domain.model.user_data.User

internal class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: AuthNetworkDataSource
) : AuthRepository {
    private var currentUser: User? = null

    override suspend fun getCurrentUser(forceUpdate: Boolean): User? {
        if (forceUpdate) {
            currentUser = networkDataSource.getCurrentUser()
        }
        return currentUser
    }

    override suspend fun login(code: String) =
        networkDataSource.login(code)

    override suspend fun logout() =
        networkDataSource.logout()

    override suspend fun deleteAccount() =
        networkDataSource.deleteAccount()
}