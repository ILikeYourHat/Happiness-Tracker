package io.github.ilikeyourhat.happinesstracker

import android.app.Application

class HappinessTrackerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DbConstructorContainer.databaseBuilder = getDatabaseBuilder(this)
    }
}
