package com.wongislandd.infinityindex.infra.viewmodels

import androidx.paging.PagingData
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseEntityPagingDataConsumerSlice : ViewModelSlice() {
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


    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is DetailsBackChannelEvent.PagingDataResUpdate<*> -> {
                handlePagingUpdate(event)
            }
        }
    }

    /**
     * Find a better way around the genericness here, so that you don't need an unchecked cast.
     * Although this is kind of safe, as long as the right entity type is passed!
     */
    @Suppress("UNCHECKED_CAST")
    private fun handlePagingUpdate(event: DetailsBackChannelEvent.PagingDataResUpdate<*>) {
        when (event.type) {
            EntityType.CHARACTERS -> {
                characterPagingData.value = event.update as PagingData<Character>
            }

            EntityType.EVENTS -> {
                eventsPagingData.value = event.update as PagingData<Event>
            }

            EntityType.CREATORS -> {
                creatorsPagingData.value = event.update as PagingData<Creator>
            }

            EntityType.STORIES -> {
                storiesPagingData.value = event.update as PagingData<Story>
            }

            EntityType.COMICS -> {
                comicPagingData.value = event.update as PagingData<Comic>
            }

            EntityType.SERIES -> {
                seriesPagingData.value = event.update as PagingData<Series>
            }
        }
    }
}