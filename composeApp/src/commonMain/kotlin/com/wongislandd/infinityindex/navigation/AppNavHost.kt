package com.wongislandd.infinityindex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wongislandd.infinityindex.entities.comics.details.ui.ComicDetailsScreen
import com.wongislandd.infinityindex.entities.comics.list.ui.ComicsListScreen

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
        composable(
            route = NavigationItem.ComicDetails.route,
            arguments = listOf(navArgument("comicId") { type = NavType.IntType })
        ) { backStackEntry ->
            val comicId = backStackEntry.arguments?.getInt("comicId") ?: 0
            ComicDetailsScreen(comicId = comicId)
        }
    }
}