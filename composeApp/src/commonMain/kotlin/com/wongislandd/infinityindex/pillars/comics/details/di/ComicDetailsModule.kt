package com.wongislandd.infinityindex.pillars.comics.details.di

import com.wongislandd.infinityindex.pillars.comics.details.helpers.NetworkFieldTypeMapper
import com.wongislandd.infinityindex.pillars.comics.details.transformers.DetailedComicTransformer
import com.wongislandd.infinityindex.pillars.comics.details.transformers.RelatedDatesTransformer
import com.wongislandd.infinityindex.pillars.comics.details.transformers.RelatedLinksTransformer
import com.wongislandd.infinityindex.pillars.comics.details.transformers.RelatedPricesTransformer
import com.wongislandd.infinityindex.pillars.comics.details.transformers.RelatedTextsTransformer
import com.wongislandd.infinityindex.pillars.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.pillars.comics.details.viewmodels.ComicDetailsBackChannelEvent
import com.wongislandd.infinityindex.pillars.comics.details.viewmodels.ComicDetailsCharactersSlice
import com.wongislandd.infinityindex.pillars.comics.details.viewmodels.ComicDetailsCreatorsSlice
import com.wongislandd.infinityindex.pillars.comics.details.viewmodels.ComicDetailsEventsSlice
import com.wongislandd.infinityindex.pillars.comics.details.viewmodels.ComicDetailsScreenStateSlice
import com.wongislandd.infinityindex.pillars.comics.details.viewmodels.ComicDetailsSlice
import com.wongislandd.infinityindex.pillars.comics.details.viewmodels.ComicDetailsStoriesSlice
import com.wongislandd.infinityindex.pillars.comics.details.viewmodels.ComicDetailsViewModel
import com.wongislandd.infinityindex.util.events.eventBusFactory
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val comicDetailsModule = module {
    eventBusFactory<ComicDetailsUiEvent>()
    eventBusFactory<ComicDetailsBackChannelEvent>()
    singleOf(::RelatedDatesTransformer)
    singleOf(::RelatedLinksTransformer)
    singleOf(::RelatedPricesTransformer)
    singleOf(::RelatedTextsTransformer)
    singleOf(::DetailedComicTransformer)
    singleOf(::NetworkFieldTypeMapper)
    singleOf(::RelatedDatesTransformer)
    factoryOf(::ComicDetailsSlice)
    factoryOf(::ComicDetailsScreenStateSlice)
    factoryOf(::ComicDetailsCharactersSlice)
    factoryOf(::ComicDetailsCreatorsSlice)
    factoryOf(::ComicDetailsEventsSlice)
    factoryOf(::ComicDetailsStoriesSlice)
    viewModelOf(::ComicDetailsViewModel)
}
