package com.wongislandd.infinityindex.pillars.events.data

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.NetworkDataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.networking.util.SupportedPillars
import com.wongislandd.infinityindex.pillars.events.models.ComicEvent
import com.wongislandd.infinityindex.pillars.events.models.NetworkComicEvent
import com.wongislandd.infinityindex.pillars.events.transformers.EventTransformer
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class EventsRepository(
    private val eventTransformer: EventTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient) {

    suspend fun getAll(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String?
    ): Resource<DataWrapper<ComicEvent>> {
        val response: Resource<NetworkDataWrapper<NetworkComicEvent>> =
            get(SupportedPillars.CHARACTERS.basePath) {
                parameter("offset", start)
                parameter("limit", count)
                searchParam?.also { searchParam ->
                    parameter("nameStartsWith", searchParam)
                }
                sortKey?.also { sortKey ->
                    parameter("orderBy", sortKey)
                }
            }
        return response.map { eventTransformer.transform(it) }
    }

    suspend fun get(
        eventId: Int
    ): Resource<ComicEvent> {
        val response: Resource<NetworkDataWrapper<NetworkComicEvent>> = get("${SupportedPillars.CHARACTERS.basePath}/$eventId")
        return response.map { eventTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    suspend fun getPagedEventsInComic(
        comicId: Int,
        start: Int,
        count: Int
    ): Resource<DataWrapper<ComicEvent>> {
        val response: Resource<NetworkDataWrapper<NetworkComicEvent>> = get("comics/$comicId/events") {
            parameter("offset", start)
            parameter("limit", count)
        }
        return response.map { eventTransformer.transform(it) }
    }

    suspend fun getEventsInComic(
        comicId: Int
    ): Resource<DataWrapper<ComicEvent>> {
        val response: Resource<NetworkDataWrapper<NetworkComicEvent>> = get("comics/$comicId/events")
        return response.map { eventTransformer.transform(it) }
    }


}