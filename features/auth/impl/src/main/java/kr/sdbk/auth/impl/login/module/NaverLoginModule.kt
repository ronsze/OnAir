package kr.sdbk.auth.impl.login.module

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.navercorp.nid.NidOAuth
import com.navercorp.nid.oauth.util.NidOAuthCallback
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
internal fun rememberNaverLoginModule(): NaverLoginModule {
    return remember { NaverLoginModule() }
}

class NaverLoginModule : LoginModule {
    override suspend fun proceed(context: Context): Result<String> =
        naverLoginWithCallback(context)

    private suspend fun naverLoginWithCallback(context: Context): Result<String> = suspendCoroutine { c ->
        NidOAuth.requestLogin(context, object : NidOAuthCallback {
            override fun onSuccess() {
                val res = NidOAuth.getAccessToken()?.run {
                    Result.success(this)
                } ?: Result.failure(IllegalStateException("naver access token is null"))

                c.resume(res)
            }

            override fun onFailure(errorCode: String, errorDesc: String) {
                c.resume(Result.failure(IllegalStateException("naver login failed: $errorCode, $errorDesc")))
            }
        })
    }
}