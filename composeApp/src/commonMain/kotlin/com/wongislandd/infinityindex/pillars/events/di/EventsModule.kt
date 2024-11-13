package com.wongislandd.infinityindex.pillars.events.di

import com.wongislandd.infinityindex.pillars.events.data.EventsRepository
import com.wongislandd.infinityindex.pillars.events.transformers.EventTransformer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val eventsModule = module {
    singleOf(::EventsRepository)
    singleOf(::EventTransformer)
}