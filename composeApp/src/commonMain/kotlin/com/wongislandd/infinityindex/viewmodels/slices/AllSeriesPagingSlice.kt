package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.repositories.SeriesEntityRepository

class AllSeriesPagingSlice(
    repository: SeriesEntityRepository
) : SeriesListPagingSlice(
    repository,
    PagedListUseCase.ALL_AVAILABLE
)