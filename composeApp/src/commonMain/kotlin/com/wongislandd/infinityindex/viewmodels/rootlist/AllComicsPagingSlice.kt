package com.wongislandd.infinityindex.viewmodels.rootlist

import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.network.NetworkComic
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository

class AllComicsPagingSlice(
    repository: ComicsEntityRepository
): BaseListPagingSlice<NetworkComic, Comic>(
    repository,
    EntityType.COMICS,
    PagedListUseCase.ALL_AVAILABLE
)