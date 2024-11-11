package com.wongislandd.infinityindex

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.navigation.AppNavHost
import com.wongislandd.infinityindex.navigation.NavHostControllerProvider
import com.wongislandd.infinityindex.themes.MarvelTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun InfinityIndexApp() {
    MarvelTheme {
        KoinContext {
            NavHostControllerProvider {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(color = MaterialTheme.colors.surface)
                ) {
                    AppNavHost()
                }
            }
        }
    }
}