package com.tellingus.tellingme.presentation.ui.feature.mypage

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.kakao.sdk.user.UserApiClient

@Composable
fun MyPageScreen() {
    var TAG: String = "로그"
    val context = LocalContext.current
    Text(text = "마이 페이지")


    Button(onClick = {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            Log.d(TAG, "가능 ${UserApiClient.instance.isKakaoTalkLoginAvailable(context)}")
        } else {
            Log.d(TAG, "불가 ${UserApiClient.instance.isKakaoTalkLoginAvailable(context)}")
            UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                if (error != null) {
                    Log.e(TAG, "로그인 실패", error)
                }
                else if (token != null) {
                    Log.i(TAG, "로그인 성공 ${token.accessToken}")
                }
            }
        }


    }) {

    }

}