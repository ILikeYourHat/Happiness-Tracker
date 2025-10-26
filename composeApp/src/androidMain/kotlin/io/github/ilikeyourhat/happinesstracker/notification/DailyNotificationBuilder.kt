package io.github.ilikeyourhat.happinesstracker.notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import io.github.ilikeyourhat.happinesstracker.R
import io.github.ilikeyourhat.happinesstracker.domain.HappinessLevel

class DailyNotificationBuilder(
    private val context: Context
) {

    fun build(): Notification {
        val customView = RemoteViews(context.packageName, R.layout.notification_daily_mood)
        customView.setOnClickPendingIntent(
            R.id.btn1,
            createPendingIntent(HappinessLevel.VERY_HAPPY)
        )
        customView.setOnClickPendingIntent(
            R.id.btn2,
            createPendingIntent(HappinessLevel.HAPPY)
        )
        customView.setOnClickPendingIntent(
            R.id.btn3,
            createPendingIntent(HappinessLevel.NEUTRAL)
        )
        customView.setOnClickPendingIntent(
            R.id.btn4,
            createPendingIntent(HappinessLevel.UNHAPPY)
        )
        customView.setOnClickPendingIntent(
            R.id.btn5,
            createPendingIntent(HappinessLevel.VERY_UNHAPPY)
        )

        return NotificationCompat.Builder(context, DAILY_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_very_happy)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(customView)
            .setAutoCancel(true)
            .build()
    }

    private fun createPendingIntent(level: HappinessLevel): PendingIntent {
        val intent = Intent(context, DailyNotificationBroadcastReceiver::class.java)
            .putExtra(EXTRA_MOOD_INDEX, level.ordinal)

        return PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}
