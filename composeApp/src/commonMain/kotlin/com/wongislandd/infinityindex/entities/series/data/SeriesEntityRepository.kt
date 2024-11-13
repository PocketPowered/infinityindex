package com.wongislandd.infinityindex.entities.series.data

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.NetworkDataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.networking.util.SupportedPillars
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.BasicEntityRepository
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.series.models.NetworkSeries
import com.wongislandd.infinityindex.entities.series.transformers.SeriesTransformer
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class SeriesEntityRepository(
    private val seriesTransformer: SeriesTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient), BasicEntityRepository<Series> {

    override suspend fun getAll(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String?
    ): Resource<DataWrapper<Series>> {
        val response: Resource<NetworkDataWrapper<NetworkSeries>> =
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
        return response.map { seriesTransformer.transform(it) }
    }

    override suspend fun get(
        id: Int
    ): Resource<Series> {
        val response: Resource<NetworkDataWrapper<NetworkSeries>> = get("${SupportedPillars.CHARACTERS.basePath}/$id")
        return response.map { seriesTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    override suspend fun getPagedEntityFromComic(
        comicId: Int,
        start: Int,
        count: Int
    ): Resource<DataWrapper<Series>> {
        val response: Resource<NetworkDataWrapper<NetworkSeries>> = get("comics/$comicId/series") {
            parameter("offset", start)
            parameter("limit", count)
        }
        return response.map { seriesTransformer.transform(it) }
    }
}