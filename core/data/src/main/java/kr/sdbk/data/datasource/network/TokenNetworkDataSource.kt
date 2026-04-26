package kr.sdbk.data.datasource.network

import kr.sdbk.domain.model.user_auth.AuthToken

interface TokenNetworkDataSource {
    suspend fun refresh(refreshToken: String): AuthToken
}
