package com.wongislandd.infinityindex.entities.comics.details.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import com.wongislandd.infinityindex.entities.comics.details.models.BaseDetailsScreenState
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.ComicDetailsViewModel
import com.wongislandd.infinityindex.infra.composables.AdditionalDetailsLazyColumn
import com.wongislandd.infinityindex.infra.composables.GlobalTopAppBar
import com.wongislandd.infinityindex.infra.composables.MarvelImage
import com.wongislandd.infinityindex.infra.composables.SectionedList
import com.wongislandd.infinityindex.infra.util.PillarModel
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ComicDetailsScreen(
    comicId: Int
) {
    DetailsScreen<ComicDetailsViewModel>(comicId)
}

@OptIn(KoinExperimentalAPI::class)
@Composable
private inline fun <reified T : SliceableViewModel<out PillarModel>> DetailsScreen(
    primaryId: Int,
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<T>()
    LaunchedEffect(Unit) {
        viewModel.uiEventBus.sendEvent(ComicDetailsUiEvent.PageInitialized(primaryId))
    }
    val screenState by viewModel.screenStateSlice.screenState.collectAsState()
    Scaffold(modifier = modifier, topBar = {
        GlobalTopAppBar()
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (val primaryRes = screenState.primaryRes) {
                is Resource.Success -> {
                    GenericDetailsScreenContents(primaryRes.data, screenState)
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
private fun GenericDetailsScreenContents(
    primaryModel: PillarModel,
    screenState: BaseDetailsScreenState<PillarModel>,
    modifier: Modifier = Modifier
) {
    val pagedCharacters = screenState.characterData.collectAsLazyPagingItems()
    val pagedCreators = screenState.creatorsData.collectAsLazyPagingItems()
    val pagedEvents = screenState.eventsData.collectAsLazyPagingItems()
    val pagedStories = screenState.storiesData.collectAsLazyPagingItems()
    val pagedSeries = screenState.seriesData.collectAsLazyPagingItems()
    val pagedComics = screenState.comicData.collectAsLazyPagingItems()

    AdditionalDetailsLazyColumn(modifier = modifier) {
        item {
            PrimaryDetailContents(primaryModel)
        }
        item {
            SectionedList(
                title = "Characters",
                pagedItems = pagedCharacters,
            )
        }
        item {
            SectionedList(
                title = "Creators",
                pagedItems = pagedCreators,
            )
        }
        item {
            SectionedList(
                title = "Events",
                pagedItems = pagedEvents,
            )
        }
        item {
            SectionedList(
                title = "Stories",
                pagedItems = pagedStories,
            )
        }
        item {
            SectionedList(
                title = "Series",
                pagedItems = pagedSeries,
            )
        }
        item {
            SectionedList(
                title = "Comics",
                pagedItems = pagedComics,
            )
        }
    }
}

@Composable
private fun PrimaryDetailContents(primaryModel: PillarModel, modifier: Modifier = Modifier) {
    when (primaryModel) {
        is Comic -> {
            ComicDetails(primaryModel, modifier)
        }
    }
}

@Composable
private fun ComicDetails(comic: Comic, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MarvelImage(image = comic.image, modifier = Modifier.fillMaxWidth())
        Text(
            text = comic.displayName,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}