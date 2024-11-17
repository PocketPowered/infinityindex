package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.PagingDataConsumerScreenState

@Composable
fun ListOfEntities(
    screenState: PagingDataConsumerScreenState,
    showAllRouteGetter: (EntityType) -> String,
    modifier: Modifier = Modifier
) {
    val entityCounts by screenState.entityCountsData.collectAsState()
    val pagedCharacters = screenState.characterData.collectAsLazyPagingItems()
    val pagedCreators = screenState.creatorsData.collectAsLazyPagingItems()
    val pagedEvents = screenState.eventsData.collectAsLazyPagingItems()
    val pagedStories = screenState.storiesData.collectAsLazyPagingItems()
    val pagedSeries = screenState.seriesData.collectAsLazyPagingItems()
    val pagedComics = screenState.comicData.collectAsLazyPagingItems()

    val sections = listOf(
        Pair(EntityType.COMICS, entityCounts.comicCount) to pagedComics,
        Pair(EntityType.CHARACTERS, entityCounts.charactersCount) to pagedCharacters,
        Pair(EntityType.EVENTS, entityCounts.eventsCount) to pagedEvents,
        Pair(EntityType.STORIES, entityCounts.storiesCount) to pagedStories,
        Pair(EntityType.SERIES, entityCounts.seriesCount) to pagedSeries,
        Pair(EntityType.CREATORS, entityCounts.creatorsCount) to pagedCreators,
    )

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        sections.forEach { (entityTypeAndCountPair, pagedItems) ->
            val entityType = entityTypeAndCountPair.first
            val totalEntityCount = entityTypeAndCountPair.second
            // Only show the section if there are items to show
            totalEntityCount?.let {
                SectionedEntityList(
                    entityType = entityType,
                    totalItemCount = totalEntityCount,
                    pagedItems = pagedItems,
                    showAllRouteGetter = showAllRouteGetter,
                )
            }
        }
    }
}