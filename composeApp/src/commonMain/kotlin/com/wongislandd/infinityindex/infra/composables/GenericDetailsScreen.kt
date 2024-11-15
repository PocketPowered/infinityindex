package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.cash.paging.compose.collectAsLazyPagingItems
import com.wongislandd.infinityindex.entities.characters.CharacterDetails
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.comics.details.models.BaseDetailsScreenState
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.details.ComicDetails
import com.wongislandd.infinityindex.entities.creators.CreatorDetails
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.EventDetails
import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.series.SeriesDetails
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.StoryDetails
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.DetailsUiEvent
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
inline fun <reified T : SliceableViewModel<out EntityModel>> GenericDetailsScreen(
    primaryId: Int,
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<T>()
    LaunchedEffect(Unit) {
        viewModel.uiEventBus.sendEvent(
            DetailsUiEvent.PageInitialized(
                primaryId,
                viewModel.entityType
            )
        )
    }
    val screenState by viewModel.screenStateSlice.screenState.collectAsState()
    Scaffold(modifier = modifier, topBar = {
        GlobalTopAppBar()
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (val primaryRes = screenState.primaryRes) {
                is Resource.Success -> {
                    AdditionalDetailsContents(primaryRes.data, screenState)
                }

                is Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is Resource.Error -> {
                    Text(
                        text = "Error loading comic details",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun AdditionalDetailsContents(
    primaryModel: EntityModel,
    screenState: BaseDetailsScreenState<EntityModel>,
    modifier: Modifier = Modifier
) {
    val pagedCharacters = screenState.characterData.collectAsLazyPagingItems()
    val pagedCreators = screenState.creatorsData.collectAsLazyPagingItems()
    val pagedEvents = screenState.eventsData.collectAsLazyPagingItems()
    val pagedStories = screenState.storiesData.collectAsLazyPagingItems()
    val pagedSeries = screenState.seriesData.collectAsLazyPagingItems()
    val pagedComics = screenState.comicData.collectAsLazyPagingItems()

    val sections = listOf(
        "Comics" to pagedComics,
        "Characters" to pagedCharacters,
        "Events" to pagedEvents,
        "Stories" to pagedStories,
        "Series" to pagedSeries,
        "Creators" to pagedCreators,
    )

    AdditionalDetailsLazyColumn(modifier = modifier) {
        item {
            PrimaryDetailContents(primaryModel)
        }
        sections.forEach { (title, pagedItems) ->
            if (pagedItems.itemCount > 0) {
                item {
                    SectionedList(
                        title = title,
                        pagedItems = pagedItems,
                    )
                }
            }
        }
    }
}

@Composable
private fun PrimaryDetailContents(primaryModel: EntityModel, modifier: Modifier = Modifier) {
    when (primaryModel) {
        is Comic -> {
            ComicDetails(primaryModel, modifier)
        }

        is Creator -> {
            CreatorDetails(primaryModel, modifier)
        }

        is Character -> {
            CharacterDetails(primaryModel, modifier)
        }

        is Story -> {
            StoryDetails(primaryModel, modifier)
        }

        is Series -> {
            SeriesDetails(primaryModel, modifier)
        }

        is Event -> {
            EventDetails(primaryModel, modifier)
        }
    }
}