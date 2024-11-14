package com.wongislandd.infinityindex.entities.comics.di

import com.wongislandd.infinityindex.repositories.ComicsEntityRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val comicsModule = module {
     singleOf(::ComicsEntityRepository)
}