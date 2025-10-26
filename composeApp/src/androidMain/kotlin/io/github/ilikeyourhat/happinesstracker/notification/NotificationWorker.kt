package io.github.ilikeyourhat.happinesstracker.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import io.github.ilikeyourhat.happinesstracker.R

class NotificationWorker(
    val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        context.setUpChannel()
        val notification = DailyNotificationBuilder(context).build()
        context.notificationManager.notify(DAILY_NOTIFICATION_ID, notification)
        return Result.success()
    }

    private fun Context.setUpChannel() {
        val channelName = context.getString(R.string.daily_notification_channel_name)
        val channel = NotificationChannel(
            DAILY_CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
    }
}
