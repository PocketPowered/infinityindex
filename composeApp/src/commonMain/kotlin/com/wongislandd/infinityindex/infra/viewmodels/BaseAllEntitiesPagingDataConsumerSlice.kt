package com.wongislandd.infinityindex.infra.viewmodels

import androidx.paging.PagingData
import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.models.local.Story
import com.wongislandd.infinityindex.infra.ListBackChannelEvent
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/**
 * Pairs well with [PagingDataConsumerScreenState]
 */
abstract class BaseAllEntitiesPagingDataConsumerSlice : ViewModelSlice() {

    protected val hasFullyLoaded: MutableStateFlow<Boolean> = MutableStateFlow(false)

    protected val entityCountsData: MutableStateFlow<EntityCountsData> =
        MutableStateFlow(EntityCountsData())

    protected val characterPagingData: MutableStateFlow<PagingData<Character>> =
        MutableStateFlow(PagingData.empty())

    protected val creatorsPagingData: MutableStateFlow<PagingData<Creator>> =
        MutableStateFlow(PagingData.empty())


    protected val eventsPagingData: MutableStateFlow<PagingData<Event>> =
        MutableStateFlow(PagingData.empty())


    protected val storiesPagingData: MutableStateFlow<PagingData<Story>> =
        MutableStateFlow(PagingData.empty())


    protected val seriesPagingData: MutableStateFlow<PagingData<Series>> =
        MutableStateFlow(PagingData.empty())

    protected val comicPagingData: MutableStateFlow<PagingData<Comic>> =
        MutableStateFlow(PagingData.empty())

    private val mapOfLoadStates = mutableMapOf(
        EntityType.CHARACTERS to false,
        EntityType.EVENTS to false,
        EntityType.CREATORS to false,
        EntityType.STORIES to false,
        EntityType.COMICS to false,
        EntityType.SERIES to false,
    )


    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is ListBackChannelEvent.PagingDataResUpdate<*> -> {
                handlePagingUpdate(event)
            }
            is ListBackChannelEvent.EntityCountsUpdate -> {
                handleEntityCountsUpdate(event)
            }
            is ListBackChannelEvent.EntityResponseReceived -> {
                handlePagingResponseSignal(event.entityType)
            }
        }
    }

    /**
     * The entity type that received a response. We will use this to determine
     * if all the entities have been loaded.
     */
    private fun handlePagingResponseSignal(entityType: EntityType) {
        mapOfLoadStates[entityType] = true
        if (mapOfLoadStates.values.all { it }) {
            hasFullyLoaded.value = true
        }
    }

    /**
     * Find a better way around the genericness here, so that you don't need an unchecked cast.
     * Although this is kind of safe, as long as the right entity type is passed!
     */
    @Suppress("UNCHECKED_CAST")
    private fun handlePagingUpdate(event: ListBackChannelEvent.PagingDataResUpdate<*>) {
        when (event.entityType) {
            EntityType.CHARACTERS -> {
                characterPagingData.value = event.update as PagingData<Character>
                mapOfLoadStates[EntityType.CHARACTERS] = true
            }

            EntityType.EVENTS -> {
                eventsPagingData.value = event.update as PagingData<Event>
                mapOfLoadStates[EntityType.EVENTS] = true
            }

            EntityType.CREATORS -> {
                creatorsPagingData.value = event.update as PagingData<Creator>
                mapOfLoadStates[EntityType.CREATORS] = true
            }

            EntityType.STORIES -> {
                storiesPagingData.value = event.update as PagingData<Story>
                mapOfLoadStates[EntityType.STORIES] = true
            }

            EntityType.COMICS -> {
                comicPagingData.value = event.update as PagingData<Comic>
                mapOfLoadStates[EntityType.COMICS] = true
            }

            EntityType.SERIES -> {
                seriesPagingData.value = event.update as PagingData<Series>
                mapOfLoadStates[EntityType.SERIES] = true
            }
        }
    }

    private fun handleEntityCountsUpdate(event: ListBackChannelEvent.EntityCountsUpdate) {
        when (event.entityType) {
            EntityType.CHARACTERS -> {
                entityCountsData.update {
                    it.copy(
                        charactersCount = event.totalCount
                    )
                }
            }
            EntityType.EVENTS -> {
                entityCountsData.update {
                    it.copy(
                        eventsCount = event.totalCount
                    )
                }
            }
            EntityType.CREATORS -> {
                entityCountsData.update {
                    it.copy(
                        creatorsCount = event.totalCount
                    )
                }
            }
            EntityType.STORIES -> {
                entityCountsData.update {
                    it.copy(
                        storiesCount = event.totalCount
                    )
                }
            }
            EntityType.COMICS -> {
                entityCountsData.update {
                    it.copy(
                        comicCount = event.totalCount
                    )
                }
            }
            EntityType.SERIES -> {
                entityCountsData.update {
                    it.copy(
                        seriesCount = event.totalCount
                    )
                }
            }
        }
    }
}