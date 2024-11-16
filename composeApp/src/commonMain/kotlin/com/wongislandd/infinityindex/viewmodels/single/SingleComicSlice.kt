package com.wongislandd.infinityindex.viewmodels.single

import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository

class SingleComicSlice(
    comicsRepository: ComicsEntityRepository
): BaseSingleEntityResolutionSlice<NetworkComic, Comic>(
    EntityType.COMICS,
    comicsRepository
)