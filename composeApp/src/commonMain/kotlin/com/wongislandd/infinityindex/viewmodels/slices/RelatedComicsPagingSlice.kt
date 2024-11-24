package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.repositories.CachingPreferenceRepository
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository

class RelatedComicsPagingSlice(
    comicsRepository: ComicsEntityRepository,
    cachingPreferenceRepository: CachingPreferenceRepository
) : ComicsListPagingSlice(
    comicsRepository,
    PagedListUseCase.RELATED_ENTITIES,
    cachingPreferenceRepository
)