package io.github.ilikeyourhat.happinesstracker

import androidx.compose.ui.window.ComposeUIViewController
import dev.zacsweers.metro.createGraph

fun MainViewController() = ComposeUIViewController {
    val appGraph = createGraph<IosAppGraph>()
    App(appGraph)
}