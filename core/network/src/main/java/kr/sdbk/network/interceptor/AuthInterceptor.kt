package kr.sdbk.network.interceptor

import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.runBlocking
import kr.sdbk.domain.logic.repository.TokenRepository
import kr.sdbk.domain.model.user_auth.AuthToken
import kr.sdbk.network.consts.APIHeader
import kr.sdbk.network.consts.APIResultCode
import okhttp3.Interceptor
import okhttp3.Response

@Singleton
class AuthInterceptor @Inject constructor(
    private val tokenRepository: TokenRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val isAccessTokenRequired =
            originalRequest.header(APIHeader.ACCESS_TOKEN_REQUIRED).equals("true", ignoreCase = true)

        val sanitizedRequest = originalRequest.newBuilder()
            .removeHeader(APIHeader.ACCESS_TOKEN_REQUIRED)
            .build()

        if (!isAccessTokenRequired) {
            return chain.proceed(sanitizedRequest)
        }

        val initialToken = runBlocking { tokenRepository.getToken() }

        val validToken = runBlocking { resolveValidToken(initialToken) }
        val requestWithToken = buildRequestWithToken(sanitizedRequest, validToken)

        val response = chain.proceed(requestWithToken)
        if (response.code != APIResultCode.NOT_AUTHORIZED) {
            return response
        }

        response.close()

        val refreshedToken = runBlocking { tryRefresh(tokenRepository.getToken() ?: initialToken) }
            ?: return chain.proceed(sanitizedRequest)

        val retryRequest = buildRequestWithToken(sanitizedRequest, refreshedToken)
        return chain.proceed(retryRequest)
    }

    private suspend fun resolveValidToken(token: AuthToken?): AuthToken? {
        token ?: return null
        if (!isExpired(token)) return token
        if (token.refreshToken.isBlank()) return null
        return tryRefresh(token)
    }

    private suspend fun tryRefresh(token: AuthToken?): AuthToken? {
        token ?: return null
        if (token.refreshToken.isBlank()) return null

        return runCatching {
            tokenRepository.refresh(token.refreshToken)
        }.onSuccess {
            tokenRepository.setToken(it)
        }.onFailure {
            tokenRepository.clearToken()
        }.getOrNull()
    }

    private fun buildRequestWithToken(
        request: okhttp3.Request,
        token: AuthToken?
    ): okhttp3.Request {
        if (token == null || token.accessToken.isBlank()) {
            return request
        }

        return request.newBuilder()
            .header(APIHeader.AUTHORIZATION, "Bearer ${token.accessToken}")
            .build()
    }

    private fun isExpired(token: AuthToken): Boolean {
        return token.expireAt <= System.currentTimeMillis()
    }
}
