package com.tellingus.tellingme.util

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging

class NotificationUtils {
    val TAG: String = "로그"

    /** Token 가져오기
     * FirebaseMessagingService의 onNewToken 메소드 역활
     */
    fun getFirebaseToken() {
        //비동기 방식
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            Log.d(TAG, "getFirebaseToken: $it")
        }
    }
}