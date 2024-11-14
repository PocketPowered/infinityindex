package com.wongislandd.infinityindex.infra.viewmodels

import androidx.paging.PagingData
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.comics.details.models.BaseDetailsScreenState
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.ComicDetailsBackChannelEvent
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.models.ComicEvent
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.PillarModel
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseScreenStateSlice<model: PillarModel> : ViewModelSlice<model>() {

    private val _characterPagingData: MutableStateFlow<PagingData<Character>> =
        MutableStateFlow(PagingData.empty())

    private val _creatorsPagingData: MutableStateFlow<PagingData<Creator>> =
        MutableStateFlow(PagingData.empty())

    private val _eventsPagingData: MutableStateFlow<PagingData<ComicEvent>> =
        MutableStateFlow(PagingData.empty())

    private val _storiesPagingData: MutableStateFlow<PagingData<Story>> =
        MutableStateFlow(PagingData.empty())

    private val _seriesPagingData: MutableStateFlow<PagingData<Series>> =
        MutableStateFlow(PagingData.empty())

    private val _comicPagingData: MutableStateFlow<PagingData<Comic>> =
        MutableStateFlow(PagingData.empty())

    private val _screenState: MutableStateFlow<BaseDetailsScreenState<PillarModel>> =
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

    val screenState: StateFlow<BaseDetailsScreenState<PillarModel>> = _screenState

    @Suppress("UNCHECKED_CAST")
    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is ComicDetailsBackChannelEvent.SingleDataResUpdate<*> -> {
                (event.update as? Resource<model>)?.let {
                    _screenState.value = _screenState.value.copy(primaryRes = it)
                }
            }

            is ComicDetailsBackChannelEvent.PagingDataResUpdate<*> -> {
                handlePagingUpdate(event)
            }
        }
    }

    /**
     * Find a better way around the genericness here, so that you don't need an unchecked cast.
     * Although this is kind of safe, as long as the right entity type is passed!
     */
    @Suppress("UNCHECKED_CAST")
    private fun handlePagingUpdate(event: ComicDetailsBackChannelEvent.PagingDataResUpdate<*>) {
        when (event.type) {
            EntityType.CHARACTERS -> {
                (event.update as? PagingData<Character>)?.let {
                    _characterPagingData.value = it
                }
            }

            EntityType.COMIC_EVENTS -> {
                (event.update as? PagingData<ComicEvent>)?.let {
                    _eventsPagingData.value = it
                }
            }

            EntityType.CREATORS -> {
                (event.update as? PagingData<Creator>)?.let {
                    _creatorsPagingData.value = it
                }
            }

            EntityType.STORIES -> {
                (event.update as? PagingData<Story>)?.let {
                    _storiesPagingData.value = it
                }
            }

            EntityType.COMICS -> {
                (event.update as? PagingData<Comic>)?.let {
                    _comicPagingData.value = it
                }
            }

            EntityType.SERIES -> {
                (event.update as? PagingData<Series>)?.let {
                    _seriesPagingData.value = it
                }
            }
        }
    }
}