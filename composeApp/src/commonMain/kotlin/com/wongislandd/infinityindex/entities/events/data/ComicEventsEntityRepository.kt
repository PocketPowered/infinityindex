package com.wongislandd.infinityindex.entities.events.data

import com.wongislandd.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.infinityindex.infra.networking.NetworkClient
import com.wongislandd.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.SupportedPillars
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.BasicEntityRepository
import com.wongislandd.infinityindex.entities.events.models.ComicEvent
import com.wongislandd.infinityindex.entities.events.models.NetworkComicEvent
import com.wongislandd.infinityindex.entities.events.transformers.EventTransformer
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class ComicEventsEntityRepository(
    private val eventTransformer: EventTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient), BasicEntityRepository<ComicEvent> {

    override suspend fun getAll(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String?
    ): Resource<DataWrapper<ComicEvent>> {
        val response: Resource<NetworkDataWrapper<NetworkComicEvent>> =
            get(SupportedPillars.COMIC_EVENTS.basePath) {
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

    override suspend fun get(
        id: Int
    ): Resource<ComicEvent> {
        val response: Resource<NetworkDataWrapper<NetworkComicEvent>> = get("${SupportedPillars.COMIC_EVENTS.basePath}/$id")
        return response.map { eventTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    override suspend fun getPagedEntityFromComic(
        comicId: Int,
        start: Int,
        count: Int
    ): Resource<DataWrapper<ComicEvent>> {
        val response: Resource<NetworkDataWrapper<NetworkComicEvent>> = get("comics/$comicId/${SupportedPillars.COMIC_EVENTS.basePath}") {
            parameter("offset", start)
            parameter("limit", count)
        }
        return response.map { eventTransformer.transform(it) }
    }

}