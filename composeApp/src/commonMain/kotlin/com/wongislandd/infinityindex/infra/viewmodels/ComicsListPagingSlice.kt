package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.ComicConstants
import com.wongislandd.infinityindex.infra.PagingBackChannelEvent
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.LookForwardDateHelper
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.network.NetworkComic
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository
import com.wongislandd.infinityindex.repositories.DataStoreRepository
import com.wongislandd.infinityindex.repositories.NumberSetting
import kotlinx.coroutines.launch

abstract class ComicsListPagingSlice(
    repository: ComicsEntityRepository,
    useCase: PagedListUseCase,
    private val dataStoreRepository: DataStoreRepository,
) : BaseListPagingSlice<NetworkComic, Comic>(
    repository, EntityType.COMICS, useCase
) {

    private var isDigitallyAvailableFilterEnabled: Boolean = false
    private var isVariantsEnabled: Boolean = false
    private var lookAheadDateRange =
        LookForwardDateHelper.getLookForwardDateRange(ComicConstants.DEFAULT_LOOK_AHEAD_DAYS)

    override fun getAdditionalPagingParams(): Map<String, Any> {
        return mapOf(
            "hasDigitalIssue" to isDigitallyAvailableFilterEnabled,
            "noVariants" to !isVariantsEnabled,
            "dateRange" to lookAheadDateRange
        )
    }

    override fun afterInit() {
        super.afterInit()
        sliceScope.launch {
            lookAheadDateRange =
                LookForwardDateHelper.getLookForwardDateRange(
                    dataStoreRepository.readIntPreference(
                        NumberSetting.LOOK_AHEAD_DAYS
                    )
                )
        }
    }

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        super.handleBackChannelEvent(event)
        when (event) {
            is PagingBackChannelEvent.SubmitDigitalAvailabilityFilterChange -> updatePagingParameters(
                digitalAvailabilityFilter = event.filterOn
            )

            is PagingBackChannelEvent.VariantsFilterChange -> updatePagingParameters(
                isVariantsEnabled = event.allowVariants
            )
        }
    }

    private fun updatePagingParameters(
        digitalAvailabilityFilter: Boolean? = null,
        isVariantsEnabled: Boolean? = null
    ) {
        var dataUpdateRequired = false
        if (digitalAvailabilityFilter != null && digitalAvailabilityFilter != isDigitallyAvailableFilterEnabled) {
            isDigitallyAvailableFilterEnabled = digitalAvailabilityFilter
            dataUpdateRequired = true
        }
        if (isVariantsEnabled != null && isVariantsEnabled != this.isVariantsEnabled) {
            this.isVariantsEnabled = isVariantsEnabled
            dataUpdateRequired = true
        }
        if (dataUpdateRequired) {
            currentPagingSource?.invalidate()
        }
    }

}