package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.infra.viewmodels.ComicsListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository
import com.wongislandd.infinityindex.repositories.DataStoreRepository

class AllComicsPagingSlice(
    repository: ComicsEntityRepository,
    dataStoreRepository: DataStoreRepository
): ComicsListPagingSlice(
    repository,
    PagedListUseCase.ALL_AVAILABLE,
    dataStoreRepository
)