package com.tellingus.tellingme.util

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.tellingus.tellingme.R
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TellingmeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val kakaoAppKey = getString(R.string.kakao_app_key)
        KakaoSdk.init(this, kakaoAppKey)
    }
}