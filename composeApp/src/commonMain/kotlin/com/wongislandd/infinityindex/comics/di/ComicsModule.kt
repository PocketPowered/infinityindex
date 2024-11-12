package com.wongislandd.infinityindex.comics.di

import com.wongislandd.infinityindex.comics.ComicsRepository
import com.wongislandd.infinityindex.comics.list.transformers.ComicDataContainerTransformer
import com.wongislandd.infinityindex.comics.list.transformers.ComicDataWrapperTransformer
import org.koin.dsl.module

val comicsModule = module {
    single<ComicDataWrapperTransformer> { ComicDataWrapperTransformer(get()) }
    single<ComicDataContainerTransformer> { ComicDataContainerTransformer(get()) }
    single<ComicsRepository> { ComicsRepository(get(), get()) }
}