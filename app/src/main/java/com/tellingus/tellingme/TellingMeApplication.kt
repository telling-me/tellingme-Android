package com.tellingus.tellingme

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.tellingus.tellingme.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TellingMeApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Kakao SDK 초기화
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
}