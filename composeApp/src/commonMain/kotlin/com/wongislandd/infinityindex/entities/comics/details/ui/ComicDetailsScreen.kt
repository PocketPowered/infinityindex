package com.wongislandd.infinityindex.entities.comics.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.wongislandd.infinityindex.infra.composables.GlobalTopAppBar
import com.wongislandd.infinityindex.infra.composables.MarvelImage
import com.wongislandd.infinityindex.infra.composables.SectionedList
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.details.models.ComicDetailsScreenState
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.ComicDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ComicDetailsScreen(
    comicId: Int
) {
    val viewModel = koinViewModel<ComicDetailsViewModel>()
    LaunchedEffect(Unit) {
        viewModel.uiEventBus.sendEvent(ComicDetailsUiEvent.PageInitialized(comicId))
    }
    val screenState by viewModel.comicDetailsScreenStateSlice.screenState.collectAsState()
    Scaffold(topBar = {
        GlobalTopAppBar()
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (val comicRes = screenState.comicRes) {
                is Resource.Success -> {
                    ComicDetailsScreenContents(comicRes.data, screenState)
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
private fun ComicDetailsScreenContents(
    comic: Comic,
    screenState: ComicDetailsScreenState,
    modifier: Modifier = Modifier
) {
    val pagedCharacters = screenState.characterData.collectAsLazyPagingItems()
    val pagedCreators = screenState.creatorsData.collectAsLazyPagingItems()
    val pagedEvents = screenState.eventsData.collectAsLazyPagingItems()
    val pagedStories = screenState.storiesData.collectAsLazyPagingItems()
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ComicDetails(
                comic,
                modifier = Modifier.fillMaxWidth()
            )
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