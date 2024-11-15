package com.wongislandd.infinityindex.infra.viewmodels

import androidx.paging.PagingData
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseDetailsScreenStateSlice<T : EntityModel> : BaseScreenStateSlice<T>,
    ViewModelSlice() {

    private val _characterPagingData: MutableStateFlow<PagingData<Character>> =
        MutableStateFlow(PagingData.empty())

    private val _creatorsPagingData: MutableStateFlow<PagingData<Creator>> =
        MutableStateFlow(PagingData.empty())

    private val _eventsPagingData: MutableStateFlow<PagingData<Event>> =
        MutableStateFlow(PagingData.empty())

    private val _storiesPagingData: MutableStateFlow<PagingData<Story>> =
        MutableStateFlow(PagingData.empty())

    private val _seriesPagingData: MutableStateFlow<PagingData<Series>> =
        MutableStateFlow(PagingData.empty())

    private val _comicPagingData: MutableStateFlow<PagingData<Comic>> =
        MutableStateFlow(PagingData.empty())

    private val _screenState: MutableStateFlow<BaseDetailsScreenState<T>> =
        MutableStateFlow(
            BaseDetailsScreenState(
                characterData = _characterPagingData,
                creatorsData = _creatorsPagingData,
                eventsData = _eventsPagingData,
                storiesData = _storiesPagingData,
                seriesData = _seriesPagingData,
                comicData = _comicPagingData
            )
        )

    val screenState: StateFlow<BaseDetailsScreenState<T>> = _screenState

    @Suppress("UNCHECKED_CAST")
    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is DetailsBackChannelEvent.SingleDataResUpdate<*> -> {
                (event.update as? Resource<T>)?.let {
                    _screenState.value = _screenState.value.copy(primaryRes = it)
                }
            }

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
                _characterPagingData.value = event.update as PagingData<Character>
            }

            EntityType.EVENTS -> {
                _eventsPagingData.value = event.update as PagingData<Event>
            }

            EntityType.CREATORS -> {
                _creatorsPagingData.value = event.update as PagingData<Creator>
            }

            EntityType.STORIES -> {
                _storiesPagingData.value = event.update as PagingData<Story>
            }

            EntityType.COMICS -> {
                _comicPagingData.value = event.update as PagingData<Comic>
            }

            EntityType.SERIES -> {
                _seriesPagingData.value = event.update as PagingData<Series>
            }
        }
    }
}