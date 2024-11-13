package com.wongislandd.infinityindex.pillars.series.data

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkClient
import com.wongislandd.infinityindex.networking.util.NetworkDataWrapper
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.networking.util.SupportedPillars
import com.wongislandd.infinityindex.pillars.series.models.Series
import com.wongislandd.infinityindex.pillars.series.models.NetworkSeries
import com.wongislandd.infinityindex.pillars.series.transformers.SeriesTransformer
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class SeriesRepository(
    private val seriesTransformer: SeriesTransformer,
    okHttpClient: HttpClient
) : NetworkClient(okHttpClient) {

    suspend fun getAll(
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

    suspend fun get(
        seriesId: Int
    ): Resource<Series> {
        val response: Resource<NetworkDataWrapper<NetworkSeries>> = get("${SupportedPillars.CHARACTERS.basePath}/$seriesId")
        return response.map { seriesTransformer.transform(it) }.map {
            it.data.results.firstOrNull()
        }
    }

    suspend fun getPagedSeriesInComic(
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

    suspend fun getSeriesInComic(
        comicId: Int
    ): Resource<DataWrapper<Series>> {
        val response: Resource<NetworkDataWrapper<NetworkSeries>> = get("comics/$comicId/series")
        return response.map { seriesTransformer.transform(it) }
    }


}