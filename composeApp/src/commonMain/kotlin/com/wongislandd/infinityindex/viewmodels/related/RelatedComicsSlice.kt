package com.wongislandd.infinityindex.viewmodels.related

import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository
import com.wongislandd.infinityindex.infra.util.EntityType

class RelatedComicsSlice(
    comicsRepository: ComicsEntityRepository
) : BaseRelatedEntitiesSlice<NetworkComic, Comic>(
    comicsRepository,
    EntityType.COMICS
)