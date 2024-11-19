package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.repositories.SeriesEntityRepository
import com.wongislandd.infinityindex.sortoptions.ComicsSortOption
import kotlinx.coroutines.launch

class ComicSeriesSupplementaryEntityResolutionSlice(
    seriesRepository: SeriesEntityRepository,
) : BaseSupplementaryEntityResolutionSlice<Series>(
    seriesRepository,
    EntityType.SERIES
) {
    override fun onSupplementaryEntityResolved(entity: Series) {
        // Send the beacon to find related comics if available
        sliceScope.launch {
            backChannelEvents.sendEvent(
                DetailsBackChannelEvent.RequestForPagination(
                    entity.id,
                    EntityType.SERIES,
                    EntityType.COMICS,
                    ComicsSortOption.FOC_DATE_ASC.sortKey
                )
            )
        }
    }
}