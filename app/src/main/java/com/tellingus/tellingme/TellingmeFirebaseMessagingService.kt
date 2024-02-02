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
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.tellingus.tellingme.presentation.ui.feature.MainActivity


class TellingmeFirebaseMessagingService : FirebaseMessagingService() {
    var TAG: String = "로그"


    /**
     * FirebaseInstanceIdService 대체
     * @title 토큰생성
     * @TODO 불필요 시 제거
     */
    override fun onNewToken(token: String) {
        Log.d(TAG, "onNewToken: $token")
    }
    // 메세지 수신
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if(remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "remoteMessage.data: ${remoteMessage.data}")
        }

        remoteMessage?.notification?.let {
            Log.d(TAG, "remoteMessage.notification: ${it.title}, ${it.body} ")
//            sendNotification(remoteMessage)
        }
    }

    // 알림 생성
    @SuppressLint("UnspecifiedImmutableFlag")
    private fun sendNotification(remoteMessage: RemoteMessage) {
        // RequestCode, Id를 고유값으로 지정하여 알림이 개별 표시되도록 함
        val uniId: Int = (System.currentTimeMillis() / 7).toInt()

        // 일회용 PendingIntent
        // PendingIntent : Intent 의 실행 권한을 외부의 어플리케이션에게 위임한다.
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Activity Stack 을 경로만 남긴다. A-B-C-D-B => A-B
        val pendingIntent = PendingIntent.getActivity(this, uniId, intent, PendingIntent.FLAG_ONE_SHOT)

        // 알림 채널 이름
        val channelId = getString(R.string.fire_base_notification_channel_id)

        // 알림 소리
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        // 알림에 대한 UI 정보와 작업을 지정한다.
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.tellingme_logo) // 아이콘 설정
            /**
             * @TODO 어떤 형식을 사용할지 백엔드 확인필요
             */
//            .setContentTitle(remoteMessage.data["title"].toString()) // 제목
//            .setContentText(remoteMessage.data["body"].toString()) // 메시지 내용
            .setContentTitle(remoteMessage.notification?.title.toString()) // 제목
            .setContentText(remoteMessage.notification?.body.toString()) // 메시지 내용

            .setAutoCancel(true)
            .setSound(soundUri) // 알림 소리
            .setContentIntent(pendingIntent) // 알림 실행 시 Intent

        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // 오레오 버전 이후에는 채널이 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Notice", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        // 알림 생성
        notificationManager.notify(uniId, notificationBuilder.build())
    }

    /** Token 가져오기 */
    fun getFirebaseToken() {
        //비동기 방식
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            Log.d(TAG, "getFirebaseToken: $it")
        }
    }

}