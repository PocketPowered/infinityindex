package com.wongislandd.infinityindex.pillars.creators.di

import com.wongislandd.infinityindex.pillars.creators.data.CreatorsRepository
import com.wongislandd.infinityindex.pillars.creators.transformers.CreatorTransformer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val creatorsModule = module {
    singleOf(::CreatorsRepository)
    singleOf(::CreatorTransformer)
}