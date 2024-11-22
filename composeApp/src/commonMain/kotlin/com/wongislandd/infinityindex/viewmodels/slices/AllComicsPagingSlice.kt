package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.infra.viewmodels.ComicsListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository

class AllComicsPagingSlice(
    repository: ComicsEntityRepository
): ComicsListPagingSlice(
    repository,
    PagedListUseCase.ALL_AVAILABLE
)