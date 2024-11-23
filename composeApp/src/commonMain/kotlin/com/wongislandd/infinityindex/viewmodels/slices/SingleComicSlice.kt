package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.network.NetworkComic
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseSingleEntityResolutionSlice
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository
import kotlinx.coroutines.launch

class SingleComicSlice(
    comicsRepository: ComicsEntityRepository
) : BaseSingleEntityResolutionSlice<NetworkComic, Comic>(
    EntityType.COMICS,
    comicsRepository
) {
    override fun onSingleEntityLoaded(entity: Comic) {
        // request for a series resolution if there is one
        entity.seriesEntityReference?.also {
            sliceScope.launch {
                backChannelEvents.sendEvent(
                    DetailsBackChannelEvent.RequestForSingleRelatedDataUpdate(
                        it.entityId,
                        EntityType.SERIES,
                    )
                )
            }
        }
    }
}