package io.github.ilikeyourhat.happinesstracker.notification

import android.app.NotificationManager
import android.content.Context
import androidx.core.content.getSystemService

val Context.notificationManager: NotificationManager
    get() = getSystemService<NotificationManager>() ?: error("NotificationManager not available")
