package com.wongislandd.infinityindex.entities

import com.wongislandd.infinityindex.entities.characters.CharacterDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.characters.CharacterDetailsViewModel
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.characters.transformers.CharacterTransformer
import com.wongislandd.infinityindex.entities.comics.details.ComicDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.comics.details.ComicDetailsViewModel
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.details.transformers.ComicTransformer
import com.wongislandd.infinityindex.entities.comics.details.transformers.RelatedDatesTransformer
import com.wongislandd.infinityindex.entities.comics.details.transformers.RelatedLinksTransformer
import com.wongislandd.infinityindex.entities.comics.details.transformers.RelatedPricesTransformer
import com.wongislandd.infinityindex.entities.comics.details.transformers.RelatedTextsTransformer
import com.wongislandd.infinityindex.entities.creators.CreatorDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.creators.CreatorDetailsViewModel
import com.wongislandd.infinityindex.entities.creators.CreatorTransformer
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.EventDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.events.EventDetailsViewModel
import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.events.transformers.EventTransformer
import com.wongislandd.infinityindex.entities.series.SeriesDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.series.SeriesDetailsViewModel
import com.wongislandd.infinityindex.entities.series.SeriesTransformer
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.StoryDetailsViewModel
import com.wongislandd.infinityindex.entities.stories.StoryDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.stories.StoryTransformer
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.DetailsBackChannelEvent
import com.wongislandd.infinityindex.infra.DetailsUiEvent
import com.wongislandd.infinityindex.infra.util.events.eventBusFactory
import com.wongislandd.infinityindex.infra.viewmodels.BaseScreenStateSlice
import com.wongislandd.infinityindex.repositories.CharactersEntityRepository
import com.wongislandd.infinityindex.repositories.ComicEventsEntityRepository
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository
import com.wongislandd.infinityindex.repositories.CreatorsEntityRepository
import com.wongislandd.infinityindex.repositories.SeriesEntityRepository
import com.wongislandd.infinityindex.repositories.StoriesEntityRepository
import com.wongislandd.infinityindex.viewmodels.CharactersResolutionSlice
import com.wongislandd.infinityindex.viewmodels.ComicResolutionSlice
import com.wongislandd.infinityindex.viewmodels.CreatorsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.EventsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.SeriesResolutionSlice
import com.wongislandd.infinityindex.viewmodels.StoriesResolutionSlice
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val entitiesModule = module {
    // Event-related
    eventBusFactory<DetailsBackChannelEvent>()
    eventBusFactory<DetailsUiEvent>()
    factoryOf<BaseScreenStateSlice<Event>>(::EventDetailsScreenStateSlice)
    factoryOf(::EventsResolutionSlice)
    singleOf(::EventTransformer)
    singleOf(::ComicEventsEntityRepository)
    viewModelOf(::EventDetailsViewModel)

    // Comic-related
    factoryOf<BaseScreenStateSlice<Comic>>(::ComicDetailsScreenStateSlice)
    factoryOf(::ComicResolutionSlice)
    singleOf(::ComicTransformer)
    singleOf(::ComicsEntityRepository)
    viewModelOf(::ComicDetailsViewModel)

    // Creator-related
    factoryOf<BaseScreenStateSlice<Creator>>(::CreatorDetailsScreenStateSlice)
    factoryOf(::CreatorsResolutionSlice)
    singleOf(::CreatorTransformer)
    singleOf(::CreatorsEntityRepository)
    viewModelOf(::CreatorDetailsViewModel)

    // Story-related
    factoryOf<BaseScreenStateSlice<Story>>(::StoryDetailsScreenStateSlice)
    factoryOf(::StoriesResolutionSlice)
    singleOf(::StoryTransformer)
    singleOf(::StoriesEntityRepository)
    viewModelOf(::StoryDetailsViewModel)

    // Series-related
    factoryOf<BaseScreenStateSlice<Series>>(::SeriesDetailsScreenStateSlice)
    factoryOf(::SeriesResolutionSlice)
    singleOf(::SeriesTransformer)
    singleOf(::SeriesEntityRepository)
    viewModelOf(::SeriesDetailsViewModel)

    // Character-related
    factoryOf<BaseScreenStateSlice<Character>>(::CharacterDetailsScreenStateSlice)
    factoryOf(::CharactersResolutionSlice)
    singleOf(::CharacterTransformer)
    singleOf(::CharactersEntityRepository)
    viewModelOf(::CharacterDetailsViewModel)

    // Other transformers and singletons
    singleOf(::RelatedDatesTransformer)
    singleOf(::RelatedLinksTransformer)
    singleOf(::RelatedPricesTransformer)
    singleOf(::RelatedTextsTransformer)
}