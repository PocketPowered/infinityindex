package com.wongislandd.infinityindex.infra.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wongislandd.infinityindex.entities.characters.CharacterDetailsViewModel
import com.wongislandd.infinityindex.entities.comics.details.ComicDetailsViewModel
import com.wongislandd.infinityindex.entities.comics.list.ui.ComicsListScreen
import com.wongislandd.infinityindex.entities.creators.CreatorDetailsViewModel
import com.wongislandd.infinityindex.entities.events.EventDetailsViewModel
import com.wongislandd.infinityindex.entities.series.SeriesDetailsViewModel
import com.wongislandd.infinityindex.entities.stories.StoryDetailsViewModel
import com.wongislandd.infinityindex.infra.composables.GenericDetailsScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = NavigationItem.ComicListScreen.route,
) {
    val navController = LocalNavHostController.current
    val pageTurnEnterTransition = slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(700)
    )

    val pageTurnExitTransition = slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(700)
    )

    val pageReturnEnterTransition = slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(700)
    )

    val pageReturnExitTransition = slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(700)
    )

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            pageTurnEnterTransition // Page turn forward transition
        },
        exitTransition = {
            pageTurnExitTransition // Page turn forward exit transition
        },
        popEnterTransition = {
            pageReturnEnterTransition // Page return transition (backward)
        },
        popExitTransition = {
            pageReturnExitTransition // Page return exit transition (backward)
        },
        modifier = modifier
    ) {
        composable(NavigationItem.ComicListScreen.route) {
            ComicsListScreen()
        }
        composable(
            route = NavigationItem.ComicDetailsScreen.route,
            arguments = listOf(navArgument("comicId") { type = NavType.IntType })
        ) { backStackEntry ->
            val comicId = backStackEntry.arguments?.getInt("comicId") ?: 0
            GenericDetailsScreen<ComicDetailsViewModel>(primaryId = comicId)
        }
        composable(
            route = NavigationItem.CreatorDetailsScreen.route,
            arguments = listOf(navArgument("creatorId") { type = NavType.IntType })
        ) { backStackEntry ->
            val creatorId = backStackEntry.arguments?.getInt("comicId") ?: 0
            GenericDetailsScreen<CreatorDetailsViewModel>(primaryId = creatorId)
        }
        composable(
            route = NavigationItem.CharacterDetailsScreen.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0
            GenericDetailsScreen<CharacterDetailsViewModel>(primaryId = characterId)
        }
        composable(
            route = NavigationItem.SeriesDetailsScreen.route,
            arguments = listOf(navArgument("seriesId") { type = NavType.IntType })
        ) { backStackEntry ->
            val seriesId = backStackEntry.arguments?.getInt("seriesId") ?: 0
            GenericDetailsScreen<SeriesDetailsViewModel>(primaryId = seriesId)
        }
        composable(
            route = NavigationItem.EventDetailsScreen.route,
            arguments = listOf(navArgument("eventId") { type = NavType.IntType })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt("eventId") ?: 0
            GenericDetailsScreen<EventDetailsViewModel>(primaryId = eventId)
        }
        composable(
            route = NavigationItem.StoryDetailsScreen.route,
            arguments = listOf(navArgument("storyId") { type = NavType.IntType })
        ) { backStackEntry ->
            val storyId = backStackEntry.arguments?.getInt("storyId") ?: 0
            GenericDetailsScreen<StoryDetailsViewModel>(primaryId = storyId)
        }
    }
}