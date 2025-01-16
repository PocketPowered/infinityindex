package com.wongislandd.infinityindex.infra.di

import com.wongislandd.infinityindex.browse.browseModule
import com.wongislandd.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.infinityindex.infra.DetailsUiEvent
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.infra.util.events.eventBusFactory
import com.wongislandd.infinityindex.infra.viewmodels.DataStoreRepository
import com.wongislandd.infinityindex.infra.viewmodels.DataStoreRepositoryImpl
import com.wongislandd.infinityindex.settings.settingsModule
import com.wongislandd.infinityindex.transformers.util.DateTransformer
import com.wongislandd.infinityindex.transformers.util.EntityReferenceTransformer
import com.wongislandd.infinityindex.transformers.util.RoledCreatorTransformer
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

fun dataStoreModule(context: Any?) = module {
    single<DataStoreRepository> { DataStoreRepositoryImpl() }
}

fun initializeKoin(context: Any? = null) =
    startKoin {
        modules(
            dataStoreModule(context),
            appModule, platformModule,
            infraModule, entitiesModule, browseModule,
            settingsModule
        )
    }