package com.wongislandd.infinityindex.pillars.events.data

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.NetworkDataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.networking.util.SupportedPillars
import com.wongislandd.infinityindex.pillars.events.models.Event
import com.wongislandd.infinityindex.pillars.events.models.NetworkEvent
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
    ): Resource<DataWrapper<Event>> {
        val response: Resource<NetworkDataWrapper<NetworkEvent>> =
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
    ): Resource<Event> {
        val response: Resource<NetworkDataWrapper<NetworkEvent>> = get("${SupportedPillars.CHARACTERS.basePath}/$eventId")
        return response.map { eventTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    suspend fun getPagedEventsInComic(
        comicId: Int,
        start: Int,
        count: Int
    ): Resource<DataWrapper<Event>> {
        val response: Resource<NetworkDataWrapper<NetworkEvent>> = get("comics/$comicId/events") {
            parameter("offset", start)
            parameter("limit", count)
        }
        return response.map { eventTransformer.transform(it) }
    }

    suspend fun getEventsInComic(
        comicId: Int
    ): Resource<DataWrapper<Event>> {
        val response: Resource<NetworkDataWrapper<NetworkEvent>> = get("comics/$comicId/events")
        return response.map { eventTransformer.transform(it) }
    }


}