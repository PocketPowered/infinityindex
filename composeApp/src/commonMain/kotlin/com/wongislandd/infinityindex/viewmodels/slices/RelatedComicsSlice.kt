package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.infra.viewmodels.ComicsListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository

class RelatedComicsSlice(
    comicsRepository: ComicsEntityRepository
) : ComicsListPagingSlice(
    comicsRepository,
    PagedListUseCase.RELATED_ENTITIES
)