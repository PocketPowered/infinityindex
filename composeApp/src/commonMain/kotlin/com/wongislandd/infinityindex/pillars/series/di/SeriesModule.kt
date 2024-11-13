package com.wongislandd.infinityindex.pillars.series.di

import com.wongislandd.infinityindex.pillars.series.data.SeriesRepository
import com.wongislandd.infinityindex.pillars.series.transformers.SeriesTransformer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val seriesModule = module {
    singleOf(::SeriesRepository)
    singleOf(::SeriesTransformer)
}