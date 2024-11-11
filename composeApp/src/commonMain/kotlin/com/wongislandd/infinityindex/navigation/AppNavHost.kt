package com.wongislandd.infinityindex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wongislandd.infinityindex.comics.list.ui.ComicsListScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = NavigationItem.ComicList.route,
) {
    val navController = LocalNavHostController.current
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.ComicList.route) {
            ComicsListScreen()
        }
    }
}