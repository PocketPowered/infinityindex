package com.wongislandd.infinityindex.home

import com.wongislandd.infinityindex.infra.PagingBackChannelEvent
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.NetworkError
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseAllEntitiesPagingDataConsumerSlice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class HomeScreenStateSlice : BaseAllEntitiesPagingDataConsumerSlice() {

    private val firstResponseTracker: MutableMap<EntityType, Resource<Any>> = mutableMapOf(
        EntityType.CHARACTERS to Resource.Loading,
        EntityType.CREATORS to Resource.Loading,
        EntityType.EVENTS to Resource.Loading,
        EntityType.STORIES to Resource.Loading,
        EntityType.SERIES to Resource.Loading,
        EntityType.COMICS to Resource.Loading
    )

    private val _screenState: MutableStateFlow<HomeScreenState> =
        MutableStateFlow(
            HomeScreenState(
                isHomeScreenLoading = true,
                characterData = characterPagingData,
                creatorsData = creatorsPagingData,
                eventsData = eventsPagingData,
                storiesData = storiesPagingData,
                seriesData = seriesPagingData,
                comicData = comicPagingData,
                entityCountsData = entityCountsData
            )
        )
    val screenState: StateFlow<HomeScreenState> = _screenState

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        super.handleBackChannelEvent(event)
        when (event) {
            is PagingBackChannelEvent.PagingResponseReceived -> {
                firstResponseTracker[event.entityType] = event.response
                if (firstResponseTracker.values.all { it !is Resource.Loading }) {
                    _screenState.update { it.copy(isHomeScreenLoading = false) }
                    maybeDisplayErrorIfNoneLoaded()
                }
            }
        }
    }

    /**
     * Once we receive something for each entity type, if all are empty we may want to
     * show an error state.
     */
    private fun maybeDisplayErrorIfNoneLoaded() {
        if (firstResponseTracker.values.all { it is Resource.Error }) {
            _screenState.update { it.copy(errorData = NetworkError.COULD_NOT_CONTACT_MARVEL_API.displayMessage) }
        }
    }

}