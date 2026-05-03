package kr.sdbk.auth.impl.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import kr.sdbk.auth.impl.BuildConfig
import java.net.URLEncoder

@Composable
internal fun rememberLoginModule(): LoginModule {
    val module = remember { LoginModule() }
    return module
}

internal class LoginModule() {
    companion object {
        private const val BASE_URL = "https://chzzk.naver.com/account-interlock"
        private const val CLIENT_ID: String = BuildConfig.chzzkClientId
        private const val REDIRECT_URI: String = BuildConfig.chzzkRedirectUri
        private const val STATE: String = BuildConfig.chzzkState
    }

    var url: String by mutableStateOf("")
    private set

    fun proceed() {
        url = getLoginUrl()
    }

    fun clear() {
        url = ""
    }

    private fun getLoginUrl() = BASE_URL.toUri().buildUpon()
        .appendQueryParameter("clientId", CLIENT_ID)
        .appendQueryParameter("redirectUri", URLEncoder.encode(REDIRECT_URI, Charsets.UTF_8.name()))
        .appendQueryParameter("state", STATE)
        .build()
        .toString()
}