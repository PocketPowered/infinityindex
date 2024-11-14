package com.wongislandd.infinityindex.entities.creators

import com.wongislandd.infinityindex.repositories.CreatorsEntityRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val creatorsModule = module {
    singleOf(::CreatorsEntityRepository)
    singleOf(::CreatorTransformer)
}