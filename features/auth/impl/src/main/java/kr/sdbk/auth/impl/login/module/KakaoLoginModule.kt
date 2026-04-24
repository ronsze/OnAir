package kr.sdbk.auth.impl.login.module

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
internal fun rememberKakaoLoginModule(): KakaoLoginModule {
    return remember { KakaoLoginModule() }
}

internal class KakaoLoginModule : LoginModule {
    override suspend fun proceed(context: Context): Result<String> = if (isKakaoTalkInstalled(context)) {
        loginWithKakaoTalk(context)
    } else {
        loginWithKakaoAccount(context)
    }

    private suspend fun loginWithKakaoTalk(context: Context): Result<String> = suspendCoroutine { c ->
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            c.resume(handleKakaoLoginResult(token, error))
        }
    }

    private suspend fun loginWithKakaoAccount(context: Context): Result<String> = suspendCoroutine { c ->
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            c.resume(handleKakaoLoginResult(token, error))
        }
    }

    private fun handleKakaoLoginResult(token: OAuthToken?, error: Throwable?) = if (error != null) {
        Result.failure(error)
    } else {
        token?.run {
            Result.success(accessToken)
        } ?: Result.failure(IllegalStateException("Token is null"))
    }

    private fun isKakaoTalkInstalled(context: Context): Boolean =
        UserApiClient.instance.isKakaoTalkLoginAvailable(context)
}