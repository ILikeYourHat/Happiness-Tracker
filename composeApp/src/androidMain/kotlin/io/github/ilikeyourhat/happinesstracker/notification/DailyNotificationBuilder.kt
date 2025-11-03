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
        val customView = RemoteViews(context.packageName, R.layout.notification_daily_mood_small)

        val customBigView = RemoteViews(context.packageName, R.layout.notification_daily_mood)
        customBigView.setOnClickPendingIntent(
            R.id.btn_mood_very_happy,
            createPendingIntent(HappinessLevel.VERY_HAPPY)
        )
        customBigView.setOnClickPendingIntent(
            R.id.btn_mood_happy,
            createPendingIntent(HappinessLevel.HAPPY)
        )
        customBigView.setOnClickPendingIntent(
            R.id.btn_mood_neutral,
            createPendingIntent(HappinessLevel.NEUTRAL)
        )
        customBigView.setOnClickPendingIntent(
            R.id.btn_mood_unhappy,
            createPendingIntent(HappinessLevel.UNHAPPY)
        )
        customBigView.setOnClickPendingIntent(
            R.id.btn_mood_very_unhappy,
            createPendingIntent(HappinessLevel.VERY_UNHAPPY)
        )

        return NotificationCompat.Builder(context, DAILY_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_mood_very_happy)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(customView)
            .setCustomBigContentView(customBigView)
            .setCustomHeadsUpContentView(customBigView)
            .setAutoCancel(true)
            .build()
    }

    private fun createPendingIntent(level: HappinessLevel): PendingIntent {
        val intent = Intent(context, DailyNotificationBroadcastReceiver::class.java)
            .putExtra(EXTRA_MOOD_INDEX, level.ordinal)

        return PendingIntent.getBroadcast(
            context,
            level.ordinal,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}
