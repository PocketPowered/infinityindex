package com.wongislandd.infinityindex.infra.viewmodels

import com.wongislandd.infinityindex.ComicConstants
import com.wongislandd.infinityindex.infra.PagingBackChannelEvent
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.network.NetworkComic
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository

abstract class ComicsListPagingSlice(
    repository: ComicsEntityRepository,
    useCase: PagedListUseCase,
) : BaseListPagingSlice<NetworkComic, Comic>(
    repository, EntityType.COMICS, useCase
) {

    private var isDigitallyAvailableFilterEnabled: Boolean = false
    private var isVariantsEnabled: Boolean = false

    override fun getAdditionalPagingParams(): Map<String, Any> {
        return mapOf(
            "hasDigitalIssue" to isDigitallyAvailableFilterEnabled,
            "noVariants" to !isVariantsEnabled,
            "dateRange" to ComicConstants.PREDEFINED_DATE_RANGE
        )
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