package com.wongislandd.infinityindex.infra.di

import com.wongislandd.infinityindex.entities.comics.details.comicDetailsModule
import com.wongislandd.infinityindex.entities.comics.di.comicsModule
import com.wongislandd.infinityindex.entities.comics.list.di.comicsListModule
import com.wongislandd.infinityindex.entities.comics.list.transformers.DateTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.entities.characters.charactersModule
import com.wongislandd.infinityindex.entities.creators.creatorsModule
import com.wongislandd.infinityindex.entities.events.eventsModule
import com.wongislandd.infinityindex.entities.series.seriesModule
import com.wongislandd.infinityindex.entities.stories.storiesModule
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
            appModule, platformModule, comicsModule, comicsListModule, comicDetailsModule,
            infraModule, resolutionSlicesModule,
            charactersModule, storiesModule, eventsModule, seriesModule, creatorsModule
        )
    }
}