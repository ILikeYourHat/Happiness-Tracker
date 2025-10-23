package io.github.ilikeyourhat.happinesstracker

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.zacsweers.metro.createGraph
import happinesstracker.composeapp.generated.resources.Res
import happinesstracker.composeapp.generated.resources.ic_launcher
import org.jetbrains.compose.resources.painterResource
import java.awt.Dimension

fun main() = application {
    val appGraph = createGraph<DesktopAppGraph>()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Happiness Tracker",
        icon = painterResource(Res.drawable.ic_launcher)
    ) {
        window.minimumSize = Dimension(WINDOW_WIDTH, WINDOW_HEIGH)
        App(appGraph)
    }
}

const val WINDOW_WIDTH = 640
const val WINDOW_HEIGH = 480
