package com.wongislandd.infinityindex.di

import com.wongislandd.infinityindex.comics.details.di.comicDetailsModule
import com.wongislandd.infinityindex.comics.di.comicsModule
import com.wongislandd.infinityindex.comics.list.di.comicsListModule
import com.wongislandd.infinityindex.comics.list.transformers.DateTransformer
import com.wongislandd.infinityindex.comics.list.transformers.ImageUrlTransformer
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val appModule = module {
    single<ImageUrlTransformer> { ImageUrlTransformer() }
    single<DateTransformer> { DateTransformer() }
}

fun initializeKoin() {
    startKoin {
        modules(appModule, platformModule, comicsModule, comicsListModule, comicDetailsModule)
    }
}