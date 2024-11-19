package com.wongislandd.infinityindex.infra.di

import com.wongislandd.infinityindex.entities.comics.transformers.DateTransformer
import com.wongislandd.infinityindex.entities.comics.transformers.EntityReferenceTransformer
import com.wongislandd.infinityindex.entities.comics.transformers.RoledCreatorTransformer
import com.wongislandd.infinityindex.entities.entitiesModule
import com.wongislandd.infinityindex.home.homeModule
import com.wongislandd.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.infinityindex.infra.DetailsUiEvent
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.infra.util.events.eventBusFactory
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


expect val platformModule: Module

val appModule = module {
    singleOf(::LoadableImageTransformer)
    singleOf(::DateTransformer)
    singleOf(::RoledCreatorTransformer)
    singleOf(::EntityReferenceTransformer)
    eventBusFactory<DetailsBackChannelEvent>()
    eventBusFactory<DetailsUiEvent>()
}

fun initializeKoin() {
    startKoin {
        modules(
            appModule, platformModule,
            infraModule, entitiesModule, homeModule
        )
    }
}