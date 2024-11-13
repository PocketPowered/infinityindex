package com.wongislandd.infinityindex.di

import com.wongislandd.infinityindex.entities.comics.details.di.comicDetailsModule
import com.wongislandd.infinityindex.entities.comics.di.comicsModule
import com.wongislandd.infinityindex.entities.comics.list.di.comicsListModule
import com.wongislandd.infinityindex.entities.comics.list.transformers.DateTransformer
import com.wongislandd.infinityindex.networking.util.ImageUrlTransformer
import com.wongislandd.infinityindex.entities.characters.di.charactersModule
import com.wongislandd.infinityindex.entities.creators.di.creatorsModule
import com.wongislandd.infinityindex.entities.events.di.eventsModule
import com.wongislandd.infinityindex.entities.series.di.seriesModule
import com.wongislandd.infinityindex.entities.stories.di.storiesModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

expect val platformModule: Module

val appModule = module {
    singleOf(::ImageUrlTransformer)
    singleOf(::DateTransformer)
}

fun initializeKoin() {
    startKoin {
        modules(
            appModule, platformModule, comicsModule, comicsListModule, comicDetailsModule,
            charactersModule, storiesModule, eventsModule, seriesModule, creatorsModule
        )
    }
}