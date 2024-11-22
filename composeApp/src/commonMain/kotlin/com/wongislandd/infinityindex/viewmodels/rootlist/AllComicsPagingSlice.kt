package com.wongislandd.infinityindex.viewmodels.rootlist

import com.wongislandd.infinityindex.infra.viewmodels.ComicsListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository

class AllComicsPagingSlice(
    repository: ComicsEntityRepository
): ComicsListPagingSlice(
    repository,
    PagedListUseCase.ALL_AVAILABLE
)