package br.usp.ime.coffee_shop_kotlin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import br.usp.ime.coffee_shop_kotlin.ui.MainActivity
import br.usp.ime.coffee_shop_kotlin.ui.SignInActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId = "notification_channel"
const val channelName = "br.usp.ime.coffee_shop_kotlin"

class FirebaseAdapter : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null) {
            val title = remoteMessage.notification?.title ?: getString(R.string.notification_title)
            val message = remoteMessage.notification?.body ?: getString(R.string.notification_body)
            generateNotification(title, message)
        }
    }


    fun generateNotification(title: String, message: String) {
        val intent = Intent(this, SignInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.logo)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 500, 1000, 500))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .setContent(getRemoteView(title, message))

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(0, builder.build())
    }

    fun getRemoteView(title: String, message: String): RemoteViews {
        val remoteView = RemoteViews(channelName, R.layout.notification)

        remoteView.setTextViewText(R.id.notificationTitle, title)
        remoteView.setTextViewText(R.id.notificationMessage, message)
        remoteView.setImageViewResource(R.id.notificationLogo, R.drawable.logo)

        return remoteView
    }
}