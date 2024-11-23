package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.repositories.SeriesEntityRepository

class RelatedSeriesPagingSlice(
    seriesRepository: SeriesEntityRepository
) : SeriesListPagingSlice(
    seriesRepository,
    PagedListUseCase.RELATED_ENTITIES
)