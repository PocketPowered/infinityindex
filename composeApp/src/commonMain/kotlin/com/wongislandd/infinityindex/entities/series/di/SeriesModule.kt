package com.wongislandd.infinityindex.entities.series.di

import com.wongislandd.infinityindex.entities.series.data.SeriesEntityRepository
import com.wongislandd.infinityindex.entities.series.transformers.SeriesTransformer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val seriesModule = module {
    singleOf(::SeriesEntityRepository)
    singleOf(::SeriesTransformer)
}