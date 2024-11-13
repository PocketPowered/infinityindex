package com.wongislandd.infinityindex.pillars.comics.di

import com.wongislandd.infinityindex.pillars.comics.details.data.ComicsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val comicsModule = module {
     singleOf(::ComicsRepository)
}