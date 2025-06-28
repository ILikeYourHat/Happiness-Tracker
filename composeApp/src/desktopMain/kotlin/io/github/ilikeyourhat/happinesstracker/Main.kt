package io.github.ilikeyourhat.happinesstracker

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.zacsweers.metro.createGraph
import java.awt.Dimension

fun main() = application {
    val appGraph = createGraph<DesktopAppGraph>()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Happiness Tracker"
    ) {
        window.minimumSize = Dimension(640, 480)
        App(appGraph)
    }
}