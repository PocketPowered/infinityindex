package com.wongislandd.infinityindex

import androidx.compose.runtime.Composable
import com.wongislandd.infinityindex.navigation.NavHostControllerProvider
import com.wongislandd.infinityindex.themes.MarvelTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    MarvelTheme {
        KoinContext {
            NavHostControllerProvider {
                InfinityIndexApp()
            }
        }
    }
}