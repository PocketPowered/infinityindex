package com.wongislandd.infinityindex.repositories

import com.wongislandd.infinityindex.models.network.NetworkSeries
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.transformers.SeriesTransformer
import com.wongislandd.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import io.ktor.client.HttpClient
import io.ktor.util.reflect.typeInfo

class SeriesEntityRepository(
    seriesTransformer: SeriesTransformer,
    okHttpClient: HttpClient
) : BaseRepository<NetworkSeries, Series>(
    seriesTransformer,
    okHttpClient,
    EntityType.SERIES,
    typeInfo<NetworkDataWrapper<NetworkSeries>>()
)