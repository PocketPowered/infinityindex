package com.wongislandd.infinityindex.browse

import com.wongislandd.infinityindex.infra.PagingBackChannelEvent
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.NetworkError
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseAllEntitiesPagingDataConsumerSlice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class BrowseScreenStateSlice : BaseAllEntitiesPagingDataConsumerSlice() {

    private val firstResponseTracker: MutableMap<EntityType, Resource<Any>> = mutableMapOf(
        EntityType.CHARACTERS to Resource.Loading,
        EntityType.CREATORS to Resource.Loading,
        EntityType.EVENTS to Resource.Loading,
        EntityType.STORIES to Resource.Loading,
        EntityType.SERIES to Resource.Loading,
        EntityType.COMICS to Resource.Loading
    )

    private val _screenState: MutableStateFlow<BrowseScreenState> =
        MutableStateFlow(
            BrowseScreenState(
                isBrowseScreenLoading = true,
                characterData = characterWrappedPagingData,
                creatorsData = creatorsWrappedPagingData,
                eventsData = eventsWrappedPagingData,
                storiesData = storiesWrappedPagingData,
                seriesData = seriesWrappedPagingData,
                comicData = comicWrappedPagingData
            )
        )
    val screenState: StateFlow<BrowseScreenState> = _screenState

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        super.handleBackChannelEvent(event)
        when (event) {
            is PagingBackChannelEvent.PagingResponseReceived -> {
                firstResponseTracker[event.entityType] = event.response
                if (firstResponseTracker.values.all { it !is Resource.Loading }) {
                    _screenState.update { it.copy(isBrowseScreenLoading = false) }
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
            val errorCounts =
                firstResponseTracker.values.map { (it as? Resource.Error)?.error }.groupingBy { it }
                    .eachCount()
            val mostCommonError = errorCounts.maxByOrNull { it.value }?.key
            _screenState.update {
                it.copy(
                    errorData = mostCommonError?.displayMessage
                        ?: NetworkError.COULD_NOT_CONTACT_MARVEL_API.displayMessage
                )
            }
        }
    }

}