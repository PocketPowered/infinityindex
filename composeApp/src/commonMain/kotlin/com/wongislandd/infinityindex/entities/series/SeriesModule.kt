package com.wongislandd.infinityindex.entities.series

import com.wongislandd.infinityindex.repositories.SeriesEntityRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val seriesModule = module {
    singleOf(::SeriesEntityRepository)
    singleOf(::SeriesTransformer)
}