package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.infra.viewmodels.ComicsListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository
import com.wongislandd.infinityindex.repositories.DataStoreRepository

class RelatedComicsSlice(
    comicsRepository: ComicsEntityRepository,
    dataStoreRepository: DataStoreRepository
) : ComicsListPagingSlice(
    comicsRepository,
    PagedListUseCase.RELATED_ENTITIES,
    dataStoreRepository
)