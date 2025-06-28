package io.github.ilikeyourhat.happinesstracker

import android.app.Application
import dev.zacsweers.metro.createGraphFactory

class HappinessTrackerApplication : Application() {

    val appGraph by lazy {
        createGraphFactory<AndroidAppGraph.Factory>().create(this)
    }
}
