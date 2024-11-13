package com.wongislandd.infinityindex.pillars.events.data

import com.wongislandd.infinityindex.networking.util.BasePagingSource
import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.events.models.ComicEvent
import com.wongislandd.infinityindex.pillars.events.models.EventsSortOption
import com.wongislandd.infinityindex.pillars.comics.list.models.SearchQuery

class EventsPagingSource(
    private val eventsRepository: EventsRepository,
    private val comicId: Int? = null,
    private val query: SearchQuery? = null,
    private val sortOption: EventsSortOption? = null
) : BasePagingSource<ComicEvent>() {
    override suspend fun fetchData(start: Int, count: Int): Resource<DataWrapper<ComicEvent>> {
        return comicId?.let {
            eventsRepository.getPagedEventsInComic(
                it,
                start,
                count
            )
        } ?: eventsRepository.getAll(
            start,
            count,
            query?.text,
            sortOption?.sortKey
        )
    }
}