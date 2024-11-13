package com.wongislandd.infinityindex.entities.creators.di

import com.wongislandd.infinityindex.entities.creators.data.CreatorsEntityRepository
import com.wongislandd.infinityindex.entities.creators.transformers.CreatorTransformer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val creatorsModule = module {
    singleOf(::CreatorsEntityRepository)
    singleOf(::CreatorTransformer)
}