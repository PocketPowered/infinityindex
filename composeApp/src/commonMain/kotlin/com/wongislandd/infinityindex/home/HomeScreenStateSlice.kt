package com.wongislandd.infinityindex.home

import com.wongislandd.infinityindex.infra.viewmodels.BaseAllEntitiesPagingDataConsumerSlice
import com.wongislandd.infinityindex.infra.viewmodels.HomeScreenState
import com.wongislandd.infinityindex.infra.viewmodels.PagingDataConsumerScreenState
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreenStateSlice: BaseAllEntitiesPagingDataConsumerSlice() {
    private val _screenState: MutableStateFlow<PagingDataConsumerScreenState> =
        MutableStateFlow(
            HomeScreenState(
                hasLoaded = hasFullyLoaded,
                characterData = characterPagingData,
                creatorsData = creatorsPagingData,
                eventsData = eventsPagingData,
                storiesData = storiesPagingData,
                seriesData = seriesPagingData,
                comicData = comicPagingData,
                entityCountsData = entityCountsData
            )
        )
    val screenState = _screenState
}