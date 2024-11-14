package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import com.wongislandd.infinityindex.entities.comics.details.data.ComicsEntityRepository
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.entities.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import kotlinx.coroutines.launch

class ComicDetailsSlice(
    comicsRepository: ComicsEntityRepository
) : BaseSlice<NetworkComic, Comic>(
    comicsRepository,
    EntityType.COMICS,
    BaseSliceType.SINGLE
)