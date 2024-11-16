package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.network.NetworkComic
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase

class RelatedComicsSlice(
    comicsRepository: ComicsEntityRepository
) : BaseListPagingSlice<NetworkComic, Comic>(
    comicsRepository,
    EntityType.COMICS,
    PagedListUseCase.RELATED_ENTITIES
)