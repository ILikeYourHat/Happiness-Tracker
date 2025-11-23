package io.github.ilikeyourhat.happinesstracker.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import io.github.ilikeyourhat.happinesstracker.R
import io.github.ilikeyourhat.happinesstracker.appGraph
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

        val db = context.appGraph.happinessDatabase
        doAsync { db.saveHappinessLevelForToday(selectedMood) }
        showConfirmationToast(context)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun BroadcastReceiver.doAsync(block: suspend CoroutineScope.() -> Unit) {
        val pendingResult = goAsync()
        GlobalScope.launch { block() }.invokeOnCompletion { pendingResult.finish() }
    }

    private fun showConfirmationToast(context: Context) {
        Toast.makeText(
            context.applicationContext,
            R.string.daily_notification_confirmation,
            Toast.LENGTH_SHORT
        ).show()
    }
}
