package com.wongislandd.infinityindex

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.wongislandd.infinityindex.di.initializeKoin
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initializeKoin()

    ComposeViewport(document.body!!) {
        App()
    }
}