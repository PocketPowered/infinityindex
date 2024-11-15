package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paging.compose.collectAsLazyPagingItems
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.PagingDataConsumerScreenState

@Composable
fun ListOfEntities(
    screenState: PagingDataConsumerScreenState,
    showAllEnabled: Boolean = false,
    modifier: Modifier = Modifier
) {
    val pagedCharacters = screenState.characterData.collectAsLazyPagingItems()
    val pagedCreators = screenState.creatorsData.collectAsLazyPagingItems()
    val pagedEvents = screenState.eventsData.collectAsLazyPagingItems()
    val pagedStories = screenState.storiesData.collectAsLazyPagingItems()
    val pagedSeries = screenState.seriesData.collectAsLazyPagingItems()
    val pagedComics = screenState.comicData.collectAsLazyPagingItems()

    val sections = listOf(
        EntityType.COMICS to pagedComics,
        EntityType.CHARACTERS to pagedCharacters,
        EntityType.EVENTS to pagedEvents,
        EntityType.STORIES to pagedStories,
        EntityType.SERIES to pagedSeries,
        EntityType.CREATORS to pagedCreators,
    )

    Column(modifier = modifier) {
        sections.forEach { (type, pagedItems) ->
            SectionedList(
                entityType = type,
                pagedItems = pagedItems,
                showAllNavRoute = type.relatedNavigationLink.takeIf { showAllEnabled }
            )
        }
    }
}