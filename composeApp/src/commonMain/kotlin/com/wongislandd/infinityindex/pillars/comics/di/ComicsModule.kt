package com.wongislandd.infinityindex.pillars.comics.di

import com.wongislandd.infinityindex.pillars.comics.details.data.ComicsRepository
import com.wongislandd.infinityindex.pillars.comics.list.transformers.BasicComicDataContainerTransformer
import com.wongislandd.infinityindex.pillars.comics.list.transformers.BasicComicDataWrapperTransformer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val comicsModule = module {
     singleOf(::BasicComicDataWrapperTransformer)
     singleOf(::BasicComicDataContainerTransformer)
     singleOf(::ComicsRepository)
}