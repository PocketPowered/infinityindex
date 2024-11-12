package com.wongislandd.infinityindex.comics.details.di

import com.wongislandd.infinityindex.comics.details.transformers.DetailedComicDataContainerTransformer
import com.wongislandd.infinityindex.comics.details.transformers.DetailedComicDataWrapperTransformer
import com.wongislandd.infinityindex.comics.details.transformers.DetailedComicTransformer
import com.wongislandd.infinityindex.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.comics.details.viewmodels.ComicDetailsBackChannelEvent
import com.wongislandd.infinityindex.comics.details.viewmodels.ComicDetailsResolutionSlice
import com.wongislandd.infinityindex.comics.details.viewmodels.ComicDetailsScreenStateSlice
import com.wongislandd.infinityindex.comics.details.viewmodels.ComicDetailsViewModel
import com.wongislandd.infinityindex.util.events.eventBusFactory
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val comicDetailsModule = module {
    eventBusFactory<ComicDetailsUiEvent>()
    eventBusFactory<ComicDetailsBackChannelEvent>()
    singleOf(::DetailedComicDataWrapperTransformer)
    singleOf(::DetailedComicDataContainerTransformer)
    singleOf(::DetailedComicTransformer)
    factoryOf(::ComicDetailsResolutionSlice)
    factoryOf(::ComicDetailsScreenStateSlice)
    viewModelOf(::ComicDetailsViewModel)
}
