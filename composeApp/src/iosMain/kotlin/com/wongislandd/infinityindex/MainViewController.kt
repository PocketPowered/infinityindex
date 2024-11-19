package com.wongislandd.infinityindex

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.wongislandd.infinityindex.themes.MarvelTheme

fun MainViewController() = ComposeUIViewController {
    MarvelTheme {
        InfinityIndexApp(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .safeDrawingPadding()
        )
    }
}