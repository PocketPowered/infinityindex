package com.wongislandd.infinityindex.home

import com.wongislandd.infinityindex.infra.viewmodels.BaseEntityPagingDataConsumerSlice
import com.wongislandd.infinityindex.infra.viewmodels.HomeScreenState
import com.wongislandd.infinityindex.infra.viewmodels.PagingDataConsumerScreenState
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreenStateSlice: BaseEntityPagingDataConsumerSlice() {
    private val _screenState: MutableStateFlow<PagingDataConsumerScreenState> =
        MutableStateFlow(
            HomeScreenState(
                characterData = characterPagingData,
                creatorsData = creatorsPagingData,
                eventsData = eventsPagingData,
                storiesData = storiesPagingData,
                seriesData = seriesPagingData,
                comicData = comicPagingData
            )
        )
}