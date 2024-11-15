package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsResolutionSlice

class ComicDetailsResolutionSlice(
    comicsRepository: ComicsEntityRepository
) : BaseDetailsResolutionSlice<NetworkComic, Comic>(
    comicsRepository,
    EntityType.COMICS
)