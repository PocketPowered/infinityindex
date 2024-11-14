package com.wongislandd.infinityindex.entities

import com.wongislandd.infinityindex.entities.comics.details.viewmodels.CharactersResolutionSlice
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.ComicResolutionSlice
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.CreatorsResolutionSlice
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.EventsResolutionSlice
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.SeriesResolutionSlice
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.StoriesResolutionSlice
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