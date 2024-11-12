package com.wongislandd.infinityindex.comics.di

import com.wongislandd.infinityindex.comics.ComicsRepository
import com.wongislandd.infinityindex.comics.list.transformers.BasicComicDataContainerTransformer
import com.wongislandd.infinityindex.comics.list.transformers.BasicComicDataWrapperTransformer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val comicsModule = module {
     singleOf(::BasicComicDataWrapperTransformer)
     singleOf(::BasicComicDataContainerTransformer)
     singleOf(::ComicsRepository)
}