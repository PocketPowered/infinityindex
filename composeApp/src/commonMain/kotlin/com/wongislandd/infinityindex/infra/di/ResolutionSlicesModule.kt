package com.wongislandd.infinityindex.infra.di

import com.wongislandd.infinityindex.viewmodels.CharactersResolutionSlice
import com.wongislandd.infinityindex.viewmodels.ComicResolutionSlice
import com.wongislandd.infinityindex.viewmodels.CreatorsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.EventsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.SeriesResolutionSlice
import com.wongislandd.infinityindex.viewmodels.StoriesResolutionSlice
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val resolutionSlicesModule = module {
    factoryOf(::ComicResolutionSlice)
    factoryOf(::ComicResolutionSlice)
    factoryOf(::SeriesResolutionSlice)
    factoryOf(::CharactersResolutionSlice)
    factoryOf(::CreatorsResolutionSlice)
    factoryOf(::EventsResolutionSlice)
    factoryOf(::StoriesResolutionSlice)
}