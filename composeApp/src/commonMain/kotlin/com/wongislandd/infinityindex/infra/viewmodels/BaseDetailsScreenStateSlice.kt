package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseDetailsScreenStateSlice<T : EntityModel> : BaseScreenStateSlice<T>,
    BaseAllEntitiesPagingDataConsumerSlice() {

    private val _supplementaryData: MutableStateFlow<EntityModel?> = MutableStateFlow(null)

    private val _screenState: MutableStateFlow<BaseDetailsScreenState<T>> =
        MutableStateFlow(
            BaseDetailsScreenState(
                supplementaryData = _supplementaryData,
                characterData = characterPagingData,
                creatorsData = creatorsPagingData,
                eventsData = eventsPagingData,
                storiesData = storiesPagingData,
                seriesData = seriesPagingData,
                comicData = comicPagingData,
                entityCountsData = entityCountsData
            )
        )

    val screenState: StateFlow<BaseDetailsScreenState<T>> = _screenState

    @Suppress("UNCHECKED_CAST")
    override fun handleBackChannelEvent(event: BackChannelEvent) {
        super.handleBackChannelEvent(event)
        when (event) {
            is DetailsBackChannelEvent.SingleDataResUpdate<*> -> {
                (event.update as? Resource<T>)?.let {
                    _screenState.value = _screenState.value.copy(primaryRes = it)
                }
            }

            is DetailsBackChannelEvent.SingleRelatedDataUpdate<*> -> {
                _supplementaryData.value = event.update
            }
        }
    }
}