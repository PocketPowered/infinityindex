package com.wongislandd.infinityindex

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.wongislandd.infinityindex.infra.di.initializeKoin

fun main() = application {

    initializeKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "infinityindex",
    ) {
        InfinityIndexApp()
    }
}