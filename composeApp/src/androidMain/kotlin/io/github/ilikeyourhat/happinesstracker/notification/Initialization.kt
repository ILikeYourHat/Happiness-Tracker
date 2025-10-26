package io.github.ilikeyourhat.happinesstracker.notification

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

fun setUpDailyNotifications(context: Context) {
    val workManager = WorkManager.getInstance(context)

    val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.DAYS)
        .setInitialDelay(DAILY_NOTIFICATION_HOUR, TimeUnit.HOURS)
        .build()
    workManager.enqueueUniquePeriodicWork(
        DAILY_NOTIFICATION_WORK_NAME,
        ExistingPeriodicWorkPolicy.REPLACE,
        workRequest
    )

    val workRequest2 = OneTimeWorkRequestBuilder<NotificationWorker>().build()
    workManager.enqueueUniqueWork(
        TEST_NOTIFICATION_WORK_NAME,
        ExistingWorkPolicy.REPLACE,
        workRequest2
    )
}
