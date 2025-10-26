package io.github.ilikeyourhat.happinesstracker

import android.app.Application
import dev.zacsweers.metro.createGraphFactory
import io.github.ilikeyourhat.happinesstracker.notification.setUpDailyNotifications

class HappinessTrackerApplication : Application() {

    val appGraph by lazy {
        createGraphFactory<AndroidAppGraph.Factory>().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        setUpDailyNotifications(this)
    }
}
