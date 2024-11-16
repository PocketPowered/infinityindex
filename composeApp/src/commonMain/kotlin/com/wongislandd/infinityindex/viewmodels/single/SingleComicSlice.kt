package com.wongislandd.infinityindex.viewmodels.single

import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.network.NetworkComic
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository

class SingleComicSlice(
    comicsRepository: ComicsEntityRepository
): BaseSingleEntityResolutionSlice<NetworkComic, Comic>(
    EntityType.COMICS,
    comicsRepository
)