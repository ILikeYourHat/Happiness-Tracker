package io.github.ilikeyourhat.happinesstracker.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.github.ilikeyourhat.happinesstracker.HappinessTrackerApplication
import io.github.ilikeyourhat.happinesstracker.domain.HappinessLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DailyNotificationBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.notificationManager.cancel(DAILY_NOTIFICATION_ID)

        val selectedIndex = intent.getIntExtra(EXTRA_MOOD_INDEX, -1)
        val selectedMood = HappinessLevel.entries.getOrNull(selectedIndex) ?: return

        val appGraph = (context.applicationContext as HappinessTrackerApplication).appGraph
        doAsync { appGraph.happinessDatabase.saveHappinessLevelForToday(selectedMood) }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun BroadcastReceiver.doAsync(block: suspend CoroutineScope.() -> Unit) {
        val pendingResult = goAsync()
        GlobalScope.launch { block() }.invokeOnCompletion { pendingResult.finish() }
    }
}
