package com.wongislandd.infinityindex.entities.events

import com.wongislandd.infinityindex.repositories.ComicEventsEntityRepository
import com.wongislandd.infinityindex.entities.events.transformers.EventTransformer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val eventsModule = module {
    singleOf(::ComicEventsEntityRepository)
    singleOf(::EventTransformer)
}