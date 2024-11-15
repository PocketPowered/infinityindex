package com.wongislandd.infinityindex.infra.di

import com.wongislandd.infinityindex.entities.comics.list.di.comicsListModule
import com.wongislandd.infinityindex.entities.comics.list.transformers.DateTransformer
import com.wongislandd.infinityindex.entities.entitiesModule
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


expect val platformModule: Module

val appModule = module {
    singleOf(::LoadableImageTransformer)
    singleOf(::DateTransformer)
}

fun initializeKoin() {
    startKoin {
        modules(
            appModule, platformModule, comicsListModule,
            infraModule, entitiesModule
        )
    }
}