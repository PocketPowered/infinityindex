package com.wongislandd.infinityindex.infra.viewmodels

import app.cash.paging.PagingConfig
import app.cash.paging.createPagingConfig
import com.wongislandd.infinityindex.ComicConstants
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.SliceableViewModel
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent

abstract class BaseDetailsViewModel<T : EntityModel>(
    val entityType: EntityType,
    val screenStateSlice: BaseDetailsScreenStateSlice<T>,
    uiEventBus: EventBus<UiEvent>,
    backChannelEventBus: EventBus<BackChannelEvent>,
    slices: List<ViewModelSlice>,
) : SliceableViewModel(
    uiEventBus,
    backChannelEventBus
) {

    init {
        val detailsPagingConfig = createPagingConfig(
            pageSize = ComicConstants.RELATED_DETAILS_MAX_ENTITY_RESULTS,
            enablePlaceholders = false
        )
        registerSlice(screenStateSlice)
        slices.forEach {
            if (it is BaseListPagingSlice<*, *>) {
                it.setPagingConfig(
                    detailsPagingConfig,
                    1
                )
            }
            registerSlice(it)
        }
    }
}