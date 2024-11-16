package com.wongislandd.infinityindex.infra.navigation

import NavigationItem
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
import com.wongislandd.infinityindex.entities.comics.ComicDetailsViewModel
import com.wongislandd.infinityindex.entities.creators.CreatorDetailsViewModel
import com.wongislandd.infinityindex.entities.events.EventDetailsViewModel
import com.wongislandd.infinityindex.entities.series.SeriesDetailsViewModel
import com.wongislandd.infinityindex.entities.stories.StoryDetailsViewModel
import com.wongislandd.infinityindex.home.HomeScreen
import com.wongislandd.infinityindex.infra.composables.GenericDetailsScreen
import com.wongislandd.infinityindex.infra.composables.GenericListScreen
import com.wongislandd.infinityindex.infra.composables.RelatedEntityListConfiguration
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.safeLet
import com.wongislandd.infinityindex.models.network.NetworkCharacter
import com.wongislandd.infinityindex.models.network.NetworkComic
import com.wongislandd.infinityindex.models.network.NetworkCreator
import com.wongislandd.infinityindex.models.network.NetworkEvent
import com.wongislandd.infinityindex.models.network.NetworkSeries
import com.wongislandd.infinityindex.models.network.NetworkStory
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedComicsListViewModel
import com.wongislandd.infinityindex.viewmodels.rootlist.AllCharactersListViewModel
import com.wongislandd.infinityindex.viewmodels.rootlist.AllComicsListViewModel
import com.wongislandd.infinityindex.viewmodels.rootlist.AllCreatorsListViewModel
import com.wongislandd.infinityindex.viewmodels.rootlist.AllEventsListViewModel
import com.wongislandd.infinityindex.viewmodels.rootlist.AllSeriesListViewModel
import com.wongislandd.infinityindex.viewmodels.rootlist.AllStoriesListViewModel


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = NavigationItem.Home.route,
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
            pageTurnEnterTransition
        },
        exitTransition = {
            pageTurnExitTransition
        },
        popEnterTransition = {
            pageReturnEnterTransition
        },
        popExitTransition = {
            pageReturnExitTransition
        },
        modifier = modifier
    ) {
        NavigationItem.entries.forEach { navItem ->
            if (navItem == NavigationItem.RelatedListScreen) {
                safeLet(navItem.idArg, navItem.idArg2, navItem.idArg3) { idArg, idArg2, idArg3 ->
                    composable(
                        route = navItem.route,
                        arguments = listOf(
                            navArgument(idArg) { type = NavType.StringType },
                            navArgument(idArg2) { type = NavType.IntType },
                            navArgument(idArg3) { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val primaryEntityType = backStackEntry.arguments?.getString(navItem.idArg)
                            ?: throw IllegalArgumentException("Missing ID argument")
                        val primaryEntityId = backStackEntry.arguments?.getInt(navItem.idArg2)
                            ?: throw IllegalArgumentException("Missing ID argument")
                        val relatedEntityType = backStackEntry.arguments?.getString(navItem.idArg3)
                            ?: throw IllegalArgumentException("Missing ID argument")

                        // TODO find a better way to parse this
                        val config = RelatedEntityListConfiguration(
                            EntityType.valueOf(primaryEntityType),
                            primaryEntityId,
                            EntityType.valueOf(relatedEntityType)
                        )
                        when (navItem) {
                            NavigationItem.RelatedListScreen -> {
                                GenericListScreen<NetworkComic, RelatedComicsListViewModel>(config)
                            }
                            else -> Unit
                        }
                    }
                }
            } else if (navItem.idArg != null) {
                composable(
                    route = navItem.route,
                    arguments = listOf(navArgument(navItem.idArg) { type = NavType.IntType })
                ) { backStackEntry ->
                    val primaryId = backStackEntry.arguments?.getInt(navItem.idArg)
                        ?: throw IllegalArgumentException("Missing ID argument")
                    when (navItem) {
                        NavigationItem.ComicDetailsScreen -> {
                            GenericDetailsScreen<ComicDetailsViewModel>(primaryId)
                        }

                        NavigationItem.CreatorDetailsScreen -> {
                            GenericDetailsScreen<CreatorDetailsViewModel>(primaryId)
                        }

                        NavigationItem.CharacterDetailsScreen -> {
                            GenericDetailsScreen<CharacterDetailsViewModel>(primaryId)
                        }

                        NavigationItem.SeriesDetailsScreen -> {
                            GenericDetailsScreen<SeriesDetailsViewModel>(primaryId)
                        }

                        NavigationItem.EventDetailsScreen -> {
                            GenericDetailsScreen<EventDetailsViewModel>(primaryId)
                        }

                        NavigationItem.StoryDetailsScreen -> {
                            GenericDetailsScreen<StoryDetailsViewModel>(primaryId)
                        }

                        else -> Unit
                    }
                }
            } else {
                composable(route = navItem.route) {
                    when (navItem) {
                        NavigationItem.AllComicListScreen -> {
                            GenericListScreen<NetworkComic, AllComicsListViewModel>()
                        }

                        NavigationItem.AllCreatorListScreen -> {
                            GenericListScreen<NetworkCreator, AllCreatorsListViewModel>()
                        }

                        NavigationItem.AllCharacterListScreen -> {
                            GenericListScreen<NetworkCharacter, AllCharactersListViewModel>()
                        }

                        NavigationItem.AllSeriesListScreen -> {
                            GenericListScreen<NetworkSeries, AllSeriesListViewModel>()
                        }

                        NavigationItem.AllEventListScreen -> {
                            GenericListScreen<NetworkEvent, AllEventsListViewModel>()
                        }

                        NavigationItem.AllStoriesListScreen -> {
                            GenericListScreen<NetworkStory, AllStoriesListViewModel>()
                        }
                        NavigationItem.Home -> {
                            HomeScreen()
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}