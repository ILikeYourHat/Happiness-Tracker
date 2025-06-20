package io.github.ilikeyourhat.happinesstracker

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Dimension

fun main() = application {
    DbConstructorContainer.databaseBuilder = getDatabaseBuilder()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Happiness Tracker"
    ) {
        window.minimumSize = Dimension(640, 480)
        App()
    }
}