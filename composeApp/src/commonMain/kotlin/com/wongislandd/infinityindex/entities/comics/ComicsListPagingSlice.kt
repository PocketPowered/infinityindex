package com.wongislandd.infinityindex.entities.comics

import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository

class ComicsListPagingSlice(
    repository: ComicsEntityRepository
): BaseListPagingSlice<NetworkComic, Comic>(
    repository,
    EntityType.COMICS
)