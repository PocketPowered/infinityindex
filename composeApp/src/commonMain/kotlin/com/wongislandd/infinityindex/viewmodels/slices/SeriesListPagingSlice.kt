package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.models.network.NetworkSeries
import com.wongislandd.infinityindex.repositories.SeriesEntityRepository

abstract class SeriesListPagingSlice(
    repository: SeriesEntityRepository,
    useCase: PagedListUseCase
) : BaseListPagingSlice<NetworkSeries, Series>(
    repository, EntityType.SERIES, useCase
) {

    override fun getAdditionalPagingParams(): Map<String, Any> {
        return mapOf(
//            "seriesType" to "ongoing"
        )
    }
}