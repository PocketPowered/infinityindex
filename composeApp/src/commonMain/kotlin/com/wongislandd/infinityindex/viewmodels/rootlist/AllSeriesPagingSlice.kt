package com.wongislandd.infinityindex.viewmodels.rootlist

import com.wongislandd.infinityindex.models.network.NetworkSeries
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.repositories.SeriesEntityRepository

class AllSeriesPagingSlice(
    repository: SeriesEntityRepository
) : BaseListPagingSlice<NetworkSeries, Series>(
    repository,
    EntityType.SERIES,
    PagedListUseCase.ALL_AVAILABLE
)