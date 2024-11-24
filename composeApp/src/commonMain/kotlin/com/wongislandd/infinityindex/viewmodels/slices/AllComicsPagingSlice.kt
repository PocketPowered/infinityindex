package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.repositories.CachingPreferenceRepository
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository

class AllComicsPagingSlice(
    repository: ComicsEntityRepository,
    cachingPreferenceRepository: CachingPreferenceRepository
): ComicsListPagingSlice(
    repository,
    PagedListUseCase.ALL_AVAILABLE,
    cachingPreferenceRepository
)