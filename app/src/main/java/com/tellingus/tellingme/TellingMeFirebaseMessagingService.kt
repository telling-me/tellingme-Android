package com.tellingus.tellingme

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.tellingus.tellingme.presentation.ui.feature.MainActivity


class TellingMeFirebaseMessagingService : FirebaseMessagingService() {
    private val NOTIFICATION_CHANNEL_ID = "tellingus_tellingme"
    private val NOTIFICATION_CHANNEL_NAME = "Notification"
    private val NOTIFICATION_CHANNEL_DESCRIPTION = "notification channel"
    var TAG: String = "로그"

    // 메세지 수신
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        /**
         * background / foreground 감지
         * @description Verify if the message contains data
         */
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "remoteMessage.data: ${remoteMessage.data}")
            sendMessageByData(remoteMessage)
        }

        /**
         * background / foreground 감지
         * background시 상태표시줄에서 표시
         * @description Verify if the message contains notification
         */
        remoteMessage?.notification?.let {
            Log.d(TAG, "remoteMessage.notification: ${it.title}, ${it.body} ")
            sendMessageByNotification(remoteMessage)
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun setNotificationIntent(): Pair<Int, PendingIntent> {
        val uniId: Int = (System.currentTimeMillis() / 7).toInt()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Activity Stack 을 경로만 남긴다. A-B-C-D-B => A-B
        val pendingIntent =
            PendingIntent.getActivity(this, uniId, intent, PendingIntent.FLAG_ONE_SHOT)
        return Pair(uniId, pendingIntent)

    }

    private fun createNotificationChannel(manager: NotificationManager) {

        var notificationChannel: NotificationChannel? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel!!.description = NOTIFICATION_CHANNEL_DESCRIPTION
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(notificationChannel!!)
        }
    }

    private fun sendMessageByData(remoteMessage: RemoteMessage) {
        val (uniId, pendingIntent) = setNotificationIntent()
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION) // 알림 소리
        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager);
        }

        // 알림에 대한 UI 정보와 작업을 지
        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.tellingme_logo) // 아이콘 설정
            .setContentTitle(remoteMessage.data["title"].toString()) // 제목
            .setContentText(remoteMessage.data["body"].toString()) // 메시지 내용
            .setAutoCancel(true)
            .setSound(soundUri) // 알림 소리
            .setContentIntent(pendingIntent) // 알림 실행 시 Intent

        notificationManager.notify(uniId, notificationBuilder.build()) // 알림 생성
    }

    private fun sendMessageByNotification(remoteMessage: RemoteMessage) {
        val (uniId, pendingIntent) = setNotificationIntent()
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION) // 알림 소리
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager);
        }

        // 알림에 대한 UI 정보와 작업을 지정
        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.tellingme_logo) // 아이콘 설정
            .setContentTitle(remoteMessage.notification?.title.toString()) // 제목
            .setContentText(remoteMessage.notification?.body.toString()) // 메시지 내용
            .setAutoCancel(true)
            .setSound(soundUri) // 알림 소리
            .setContentIntent(pendingIntent) // 알림 실행 시 Intent

        notificationManager.notify(uniId, notificationBuilder.build()) // 알림 생성
    }
}