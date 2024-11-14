package com.wongislandd.infinityindex.infra.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun NavHostControllerProvider(
    navHostController: NavHostController = rememberNavController(),
    content: @Composable NavHostController.() -> Unit
) {
    CompositionLocalProvider(LocalNavHostController provides navHostController) {
        content(navHostController)
    }
}