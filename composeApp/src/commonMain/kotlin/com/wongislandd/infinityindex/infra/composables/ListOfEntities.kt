package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paging.compose.collectAsLazyPagingItems
import com.wongislandd.infinityindex.infra.viewmodels.PagingDataConsumerScreenState

@Composable
fun ListOfEntities(
    screenState: PagingDataConsumerScreenState,
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

    Column(modifier = modifier) {
        sections.forEach { (title, pagedItems) ->
            SectionedList(
                title = title,
                pagedItems = pagedItems,
            )
        }
    }
}