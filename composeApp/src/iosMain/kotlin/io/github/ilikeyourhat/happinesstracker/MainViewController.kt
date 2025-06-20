package io.github.ilikeyourhat.happinesstracker

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    DbConstructorContainer.databaseBuilder = getDatabaseBuilder()
    App()
}