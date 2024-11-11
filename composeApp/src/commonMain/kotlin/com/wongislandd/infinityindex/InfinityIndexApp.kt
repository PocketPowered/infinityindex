package com.wongislandd.infinityindex

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.navigation.AppNavHost

@Composable
fun InfinityIndexApp(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {
        AppNavHost()
    }
}