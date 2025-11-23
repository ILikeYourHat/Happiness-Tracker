package io.github.ilikeyourhat.happinesstracker.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import io.github.ilikeyourhat.happinesstracker.R
import io.github.ilikeyourhat.happinesstracker.appGraph

class NotificationWorker(
    val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        setUpChannel()
        if (!wasMoodSelectedToday()) {
            sendNotification()
        }
        return Result.success()
    }

    private fun setUpChannel() {
        val channelName = context.getString(R.string.daily_notification_channel_name)
        val channel = NotificationChannel(
            DAILY_CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        context.notificationManager.createNotificationChannel(channel)
    }

    private fun sendNotification() {
        val notification = DailyNotificationBuilder(context).build()
        context.notificationManager.notify(DAILY_NOTIFICATION_ID, notification)
    }

    private suspend fun wasMoodSelectedToday(): Boolean {
        val db = context.appGraph.happinessDatabase
        return db.getHappinessLevelForToday() != null
    }
}
