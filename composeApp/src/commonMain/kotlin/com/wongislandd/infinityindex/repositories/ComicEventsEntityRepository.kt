package com.wongislandd.infinityindex.repositories

import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.events.models.NetworkComicEvent
import com.wongislandd.infinityindex.entities.events.transformers.EventTransformer
import com.wongislandd.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.infinityindex.infra.paging.BaseRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import io.ktor.client.HttpClient
import io.ktor.util.reflect.typeInfo

class ComicEventsEntityRepository(
    eventTransformer: EventTransformer,
    okHttpClient: HttpClient,
) : BaseRepository<NetworkComicEvent, Event>(
    eventTransformer,
    okHttpClient,
    EntityType.EVENTS,
    typeInfo<NetworkDataWrapper<NetworkComicEvent>>()
)