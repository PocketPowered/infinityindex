package com.wongislandd.infinityindex.di

import com.wongislandd.infinityindex.pillars.characters.di.charactersModule
import com.wongislandd.infinityindex.pillars.comics.details.di.comicDetailsModule
import com.wongislandd.infinityindex.pillars.comics.di.comicsModule
import com.wongislandd.infinityindex.pillars.comics.list.di.comicsListModule
import com.wongislandd.infinityindex.pillars.comics.list.transformers.DateTransformer
import com.wongislandd.infinityindex.pillars.comics.list.transformers.ImageUrlTransformer
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
            charactersModule
        )
    }
}