package com.wongislandd.infinityindex.entities

import com.wongislandd.infinityindex.entities.characters.CharacterDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.characters.CharacterDetailsViewModel
import com.wongislandd.infinityindex.entities.characters.CharactersListPagingSlice
import com.wongislandd.infinityindex.entities.characters.CharactersListViewModel
import com.wongislandd.infinityindex.entities.characters.CharactersListScreenStateSlice
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.characters.transformers.CharacterTransformer
import com.wongislandd.infinityindex.entities.comics.ComicsListScreenStateSlice
import com.wongislandd.infinityindex.entities.comics.ComicDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.comics.ComicDetailsViewModel
import com.wongislandd.infinityindex.entities.comics.ComicsListPagingSlice
import com.wongislandd.infinityindex.entities.comics.ComicsListViewModel
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.details.transformers.ComicTransformer
import com.wongislandd.infinityindex.entities.comics.details.transformers.RelatedDatesTransformer
import com.wongislandd.infinityindex.entities.comics.details.transformers.RelatedLinksTransformer
import com.wongislandd.infinityindex.entities.comics.details.transformers.RelatedPricesTransformer
import com.wongislandd.infinityindex.entities.comics.details.transformers.RelatedTextsTransformer
import com.wongislandd.infinityindex.entities.creators.CreatorDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.creators.CreatorDetailsViewModel
import com.wongislandd.infinityindex.entities.creators.CreatorsListViewModel
import com.wongislandd.infinityindex.entities.creators.CreatorTransformer
import com.wongislandd.infinityindex.entities.creators.CreatorsListPagingSlice
import com.wongislandd.infinityindex.entities.creators.CreatorsListScreenStateSlice
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.EventDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.events.EventDetailsViewModel
import com.wongislandd.infinityindex.entities.events.EventsListScreenStateSlice
import com.wongislandd.infinityindex.entities.events.EventListViewModel
import com.wongislandd.infinityindex.entities.events.EventsListPagingSlice
import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.events.transformers.EventTransformer
import com.wongislandd.infinityindex.entities.series.SeriesDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.series.SeriesDetailsViewModel
import com.wongislandd.infinityindex.entities.series.SeriesListListPagingSlice
import com.wongislandd.infinityindex.entities.series.SeriesListScreenStateSlice
import com.wongislandd.infinityindex.entities.series.SeriesListViewModel
import com.wongislandd.infinityindex.entities.series.SeriesTransformer
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.StoriesListListPagingSlice
import com.wongislandd.infinityindex.entities.stories.StoriesListScreenStateSlice
import com.wongislandd.infinityindex.entities.stories.StoriesListViewModel
import com.wongislandd.infinityindex.entities.stories.StoryDetailsViewModel
import com.wongislandd.infinityindex.entities.stories.StoryDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.stories.StoryTransformer
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.infinityindex.repositories.CharactersEntityRepository
import com.wongislandd.infinityindex.repositories.EventsEntityRepository
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository
import com.wongislandd.infinityindex.repositories.CreatorsEntityRepository
import com.wongislandd.infinityindex.repositories.SeriesEntityRepository
import com.wongislandd.infinityindex.repositories.StoriesEntityRepository
import com.wongislandd.infinityindex.viewmodels.CharactersDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.ComicDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.CreatorsDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.EventsDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.SeriesDetailsResolutionSlice
import com.wongislandd.infinityindex.viewmodels.StoriesDetailsResolutionSlice
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val entitiesModule = module {
    // Shared
    factoryOf(::SortSlice)
    factoryOf(::SearchSlice)

    // Event-related
    factoryOf<BaseDetailsScreenStateSlice<Event>>(::EventDetailsScreenStateSlice)
    factoryOf(::EventsDetailsResolutionSlice)
    factoryOf<BaseListScreenStateSlice<Event>>(::EventsListScreenStateSlice)
    factoryOf(::EventsListPagingSlice)
    singleOf(::EventTransformer)
    singleOf(::EventsEntityRepository)
    viewModelOf(::EventDetailsViewModel)
    viewModelOf(::EventListViewModel)

    // Comic-related
    factoryOf<BaseDetailsScreenStateSlice<Comic>>(::ComicDetailsScreenStateSlice)
    factoryOf<BaseListScreenStateSlice<Comic>>(::ComicsListScreenStateSlice)
    factoryOf(::ComicDetailsResolutionSlice)
    factoryOf(::ComicsListPagingSlice)
    singleOf(::ComicTransformer)
    singleOf(::ComicsEntityRepository)
    viewModelOf(::ComicDetailsViewModel)
    viewModelOf(::ComicsListViewModel)

    // Creator-related
    factoryOf<BaseDetailsScreenStateSlice<Creator>>(::CreatorDetailsScreenStateSlice)
    factoryOf<BaseListScreenStateSlice<Creator>>(::CreatorsListScreenStateSlice)
    factoryOf(::CreatorsDetailsResolutionSlice)
    factoryOf(::CreatorsListPagingSlice)
    singleOf(::CreatorTransformer)
    singleOf(::CreatorsEntityRepository)
    viewModelOf(::CreatorDetailsViewModel)
    viewModelOf(::CreatorsListViewModel)

    // Story-related
    factoryOf<BaseDetailsScreenStateSlice<Story>>(::StoryDetailsScreenStateSlice)
    factoryOf<BaseListScreenStateSlice<Story>>(::StoriesListScreenStateSlice)
    factoryOf(::StoriesDetailsResolutionSlice)
    factoryOf(::StoriesListListPagingSlice)
    singleOf(::StoryTransformer)
    singleOf(::StoriesEntityRepository)
    viewModelOf(::StoryDetailsViewModel)
    viewModelOf(::StoriesListViewModel)

    // Series-related
    factoryOf<BaseDetailsScreenStateSlice<Series>>(::SeriesDetailsScreenStateSlice)
    factoryOf<BaseListScreenStateSlice<Series>>(::SeriesListScreenStateSlice)
    factoryOf(::SeriesDetailsResolutionSlice)
    factoryOf(::SeriesListListPagingSlice)
    singleOf(::SeriesTransformer)
    singleOf(::SeriesEntityRepository)
    viewModelOf(::SeriesDetailsViewModel)
    viewModelOf(::SeriesListViewModel)

    // Character-related
    factoryOf<BaseDetailsScreenStateSlice<Character>>(::CharacterDetailsScreenStateSlice)
    factoryOf<BaseListScreenStateSlice<Character>>(::CharactersListScreenStateSlice)
    factoryOf(::CharactersDetailsResolutionSlice)
    factoryOf(::CharactersListPagingSlice)
    singleOf(::CharacterTransformer)
    singleOf(::CharactersEntityRepository)
    viewModelOf(::CharacterDetailsViewModel)
    viewModelOf(::CharactersListViewModel)

    // Other transformers and singletons
    singleOf(::RelatedDatesTransformer)
    singleOf(::RelatedLinksTransformer)
    singleOf(::RelatedPricesTransformer)
    singleOf(::RelatedTextsTransformer)

}