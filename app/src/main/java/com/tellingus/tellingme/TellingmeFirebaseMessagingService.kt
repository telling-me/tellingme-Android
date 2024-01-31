package com.tellingus.tellingme

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.tellingus.tellingme.util.sendNotification

class TellingmeFirebaseMessagingService : FirebaseMessagingService() {
    var TAG: String = "로그"
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "onMessageReceived: From${remoteMessage?.from}")

        remoteMessage?.data?.let {
            Log.d(TAG, "onMessageReceived: Messge data payload: ${remoteMessage.data}")
        }

        remoteMessage?.notification?.let {
            Log.d(TAG, "onMessageReceived: Message Notifiction Body: ${it.body}")

        }
    }

}