package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import com.wongislandd.infinityindex.entities.comics.details.data.ComicsEntityRepository
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseResolutionSlice

class ComicResolutionSlice(
    comicsRepository: ComicsEntityRepository
) : BaseResolutionSlice<NetworkComic, Comic>(
    comicsRepository,
    EntityType.COMICS
)