package com.wongislandd.infinityindex.entities.series.data

import com.wongislandd.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.infinityindex.infra.networking.NetworkClient
import com.wongislandd.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.SupportedPillars
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
            get(SupportedPillars.SERIES.basePath) {
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
        val response: Resource<NetworkDataWrapper<NetworkSeries>> = get("${SupportedPillars.SERIES.basePath}/$id")
        return response.map { seriesTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    override suspend fun getPagedEntityFromComic(
        comicId: Int,
        start: Int,
        count: Int
    ): Resource<DataWrapper<Series>> {
        val response: Resource<NetworkDataWrapper<NetworkSeries>> = get("comics/$comicId/${SupportedPillars.SERIES.basePath}") {
            parameter("offset", start)
            parameter("limit", count)
        }
        return response.map { seriesTransformer.transform(it) }
    }
}