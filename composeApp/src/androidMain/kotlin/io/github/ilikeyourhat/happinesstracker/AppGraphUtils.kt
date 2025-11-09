package io.github.ilikeyourhat.happinesstracker

import android.content.Context

val Context.appGraph: AndroidAppGraph
    get() = (applicationContext as HappinessTrackerApplication).appGraph
