package com.wongislandd.infinityindex.entities.comics.details.di

import com.wongislandd.infinityindex.entities.comics.details.helpers.NetworkFieldTypeMapper
import com.wongislandd.infinityindex.entities.comics.details.transformers.DetailedComicTransformer
import com.wongislandd.infinityindex.entities.comics.details.transformers.RelatedDatesTransformer
import com.wongislandd.infinityindex.entities.comics.details.transformers.RelatedLinksTransformer
import com.wongislandd.infinityindex.entities.comics.details.transformers.RelatedPricesTransformer
import com.wongislandd.infinityindex.entities.comics.details.transformers.RelatedTextsTransformer
import com.wongislandd.infinityindex.infra.DetailsUiEvent
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.ComicDetailsBackChannelEvent
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.CharactersResolutionSlice
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.CreatorsResolutionSlice
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.EventsResolutionSlice
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.ComicDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.SeriesResolutionSlice
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.ComicResolutionSlice
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.StoriesResolutionSlice
import com.wongislandd.infinityindex.entities.comics.details.viewmodels.ComicDetailsViewModel
import com.wongislandd.infinityindex.infra.util.events.eventBusFactory
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val comicDetailsModule = module {
    eventBusFactory<DetailsUiEvent>()
    eventBusFactory<ComicDetailsBackChannelEvent>()
    singleOf(::RelatedDatesTransformer)
    singleOf(::RelatedLinksTransformer)
    singleOf(::RelatedPricesTransformer)
    singleOf(::RelatedTextsTransformer)
    singleOf(::DetailedComicTransformer)
    singleOf(::NetworkFieldTypeMapper)
    singleOf(::RelatedDatesTransformer)
    factoryOf(::ComicResolutionSlice)
    factoryOf(::SeriesResolutionSlice)
    factoryOf(::ComicDetailsScreenStateSlice)
    factoryOf(::CharactersResolutionSlice)
    factoryOf(::CreatorsResolutionSlice)
    factoryOf(::EventsResolutionSlice)
    factoryOf(::StoriesResolutionSlice)
    viewModelOf(::ComicDetailsViewModel)
}
