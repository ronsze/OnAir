package kr.sdbk.auth.impl.login.module

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kr.sdbk.domain.model.SocialType

@Composable
internal fun rememberLoginClient(): LoginClient {
    return remember { LoginClient() }
}

internal class LoginClient {
    suspend fun login(context: Context, socialType: SocialType): Pair<Result<String>, SocialType> {
        val module = when (socialType) {
            SocialType.NAVER -> NaverLoginModule()
            SocialType.KAKAO -> KakaoLoginModule()
            SocialType.GOOGLE -> GoogleLoginModule()
        }
        val res = module.proceed(context)
        return res to socialType
    }
}

internal interface LoginModule {
    suspend fun proceed(context: Context): Result<String>
}