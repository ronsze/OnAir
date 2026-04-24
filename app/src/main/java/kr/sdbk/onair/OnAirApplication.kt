package kr.sdbk.onair

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NidOAuth
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OnAirApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initFirebaseSDK()
        initNaverSDK()
        initKakaoSDK()
    }

    private fun initFirebaseSDK() {
        Firebase.initialize(this)
    }

    private fun initNaverSDK() {
        NidOAuth.initialize(this, BuildConfig.naverClientId, BuildConfig.naverClientSecret, BuildConfig.naverClientName)
    }

    private fun initKakaoSDK() {
        KakaoSdk.init(this, BuildConfig.kakaoNativeAppKey)
    }
}