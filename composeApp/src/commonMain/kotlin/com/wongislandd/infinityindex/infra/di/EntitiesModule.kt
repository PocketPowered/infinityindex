package com.wongislandd.infinityindex.infra.di

import com.wongislandd.infinityindex.viewmodels.slices.CharacterDetailsScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.CharacterDetailsViewModel
import com.wongislandd.infinityindex.viewmodels.slices.ComicDetailsScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.ComicDetailsViewModel
import com.wongislandd.infinityindex.transformers.util.RelatedDatesTransformer
import com.wongislandd.infinityindex.transformers.util.RelatedLinksTransformer
import com.wongislandd.infinityindex.transformers.util.RelatedPricesTransformer
import com.wongislandd.infinityindex.transformers.util.RelatedTextsTransformer
import com.wongislandd.infinityindex.viewmodels.slices.CreatorDetailsScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.CreatorDetailsViewModel
import com.wongislandd.infinityindex.viewmodels.slices.EventDetailsScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.EventDetailsViewModel
import com.wongislandd.infinityindex.viewmodels.slices.SeriesDetailsScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.SeriesDetailsViewModel
import com.wongislandd.infinityindex.viewmodels.slices.StoriesListScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.StoryDetailsViewModel
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.slices.ComicsListScreenStateSlice
import com.wongislandd.infinityindex.infra.viewmodels.ComicSeriesSupplementaryEntityResolutionSlice
import com.wongislandd.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.models.local.Story
import com.wongislandd.infinityindex.repositories.CharactersEntityRepository
import com.wongislandd.infinityindex.repositories.ComicsEntityRepository
import com.wongislandd.infinityindex.repositories.CreatorsEntityRepository
import com.wongislandd.infinityindex.repositories.EventsEntityRepository
import com.wongislandd.infinityindex.repositories.SeriesEntityRepository
import com.wongislandd.infinityindex.repositories.StoriesEntityRepository
import com.wongislandd.infinityindex.transformers.CharacterTransformer
import com.wongislandd.infinityindex.transformers.ComicTransformer
import com.wongislandd.infinityindex.transformers.CreatorTransformer
import com.wongislandd.infinityindex.transformers.EventTransformer
import com.wongislandd.infinityindex.transformers.SeriesTransformer
import com.wongislandd.infinityindex.transformers.StoryTransformer
import com.wongislandd.infinityindex.viewmodels.RelatedCharactersListViewModel
import com.wongislandd.infinityindex.viewmodels.slices.RelatedCharactersSlice
import com.wongislandd.infinityindex.viewmodels.RelatedComicsListViewModel
import com.wongislandd.infinityindex.viewmodels.slices.RelatedComicsSlice
import com.wongislandd.infinityindex.viewmodels.RelatedCreatorsListViewModel
import com.wongislandd.infinityindex.viewmodels.slices.RelatedCreatorsSlice
import com.wongislandd.infinityindex.viewmodels.RelatedEventsListViewModel
import com.wongislandd.infinityindex.viewmodels.slices.RelatedEventsSlice
import com.wongislandd.infinityindex.viewmodels.RelatedSeriesListViewModel
import com.wongislandd.infinityindex.viewmodels.slices.RelatedSeriesSlice
import com.wongislandd.infinityindex.viewmodels.RelatedStoriesListViewModel
import com.wongislandd.infinityindex.viewmodels.slices.RelatedStoriesSlice
import com.wongislandd.infinityindex.viewmodels.AllCharactersListViewModel
import com.wongislandd.infinityindex.viewmodels.slices.AllCharactersPagingSlice
import com.wongislandd.infinityindex.viewmodels.AllComicsListViewModel
import com.wongislandd.infinityindex.viewmodels.slices.AllComicsPagingSlice
import com.wongislandd.infinityindex.viewmodels.AllCreatorsListViewModel
import com.wongislandd.infinityindex.viewmodels.slices.AllCreatorsPagingSlice
import com.wongislandd.infinityindex.viewmodels.AllEventsListViewModel
import com.wongislandd.infinityindex.viewmodels.slices.AllEventsPagingSlice
import com.wongislandd.infinityindex.viewmodels.AllSeriesListViewModel
import com.wongislandd.infinityindex.viewmodels.slices.AllSeriesPagingSlice
import com.wongislandd.infinityindex.viewmodels.AllStoriesListViewModel
import com.wongislandd.infinityindex.viewmodels.slices.AllStoriesPagingSlice
import com.wongislandd.infinityindex.viewmodels.slices.CharactersListScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.slices.CreatorsListScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.slices.EventsListScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.slices.SeriesListScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.slices.StoryDetailsScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.slices.SingleCharacterSlice
import com.wongislandd.infinityindex.viewmodels.slices.SingleComicSlice
import com.wongislandd.infinityindex.viewmodels.slices.SingleCreatorSlice
import com.wongislandd.infinityindex.viewmodels.slices.SingleEventSlice
import com.wongislandd.infinityindex.viewmodels.slices.SingleSeriesSlice
import com.wongislandd.infinityindex.viewmodels.slices.SingleStorySlice
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
    factoryOf(::EventsListScreenStateSlice)
    factoryOf(::AllEventsPagingSlice)
    factoryOf(::RelatedEventsSlice)
    factoryOf(::SingleEventSlice)
    singleOf(::EventTransformer)
    singleOf(::EventsEntityRepository)
    viewModelOf(::EventDetailsViewModel)
    viewModelOf(::AllEventsListViewModel)
    viewModelOf(::RelatedEventsListViewModel)


    // Comic-related
    factoryOf<BaseDetailsScreenStateSlice<Comic>>(::ComicDetailsScreenStateSlice)
    factoryOf(::ComicsListScreenStateSlice)
    factoryOf(::RelatedComicsSlice)
    factoryOf(::AllComicsPagingSlice)
    factoryOf(::SingleComicSlice)
    singleOf(::ComicTransformer)
    singleOf(::ComicsEntityRepository)
    viewModelOf(::ComicDetailsViewModel)
    viewModelOf(::AllComicsListViewModel)
    viewModelOf(::RelatedComicsListViewModel)

    // Creator-related
    factoryOf<BaseDetailsScreenStateSlice<Creator>>(::CreatorDetailsScreenStateSlice)
    factoryOf(::CreatorsListScreenStateSlice)
    factoryOf(::RelatedCreatorsSlice)
    factoryOf(::AllCreatorsPagingSlice)
    factoryOf(::SingleCreatorSlice)
    singleOf(::CreatorTransformer)
    singleOf(::CreatorsEntityRepository)
    viewModelOf(::CreatorDetailsViewModel)
    viewModelOf(::AllCreatorsListViewModel)
    viewModelOf(::RelatedCreatorsListViewModel)

    // Story-related
    factoryOf<BaseDetailsScreenStateSlice<Story>>(::StoryDetailsScreenStateSlice)
    factoryOf(::StoriesListScreenStateSlice)
    factoryOf(::RelatedStoriesSlice)
    factoryOf(::AllStoriesPagingSlice)
    factoryOf(::SingleStorySlice)
    singleOf(::StoryTransformer)
    singleOf(::StoriesEntityRepository)
    viewModelOf(::StoryDetailsViewModel)
    viewModelOf(::AllStoriesListViewModel)
    viewModelOf(::RelatedStoriesListViewModel)

    // Series-related
    factoryOf<BaseDetailsScreenStateSlice<Series>>(::SeriesDetailsScreenStateSlice)
    factoryOf(::SeriesListScreenStateSlice)
    factoryOf(::RelatedSeriesSlice)
    factoryOf(::SingleSeriesSlice)
    factoryOf(::AllSeriesPagingSlice)
    factoryOf(::ComicSeriesSupplementaryEntityResolutionSlice)

    singleOf(::SeriesTransformer)
    singleOf(::SeriesEntityRepository)
    viewModelOf(::SeriesDetailsViewModel)
    viewModelOf(::AllSeriesListViewModel)
    viewModelOf(::RelatedSeriesListViewModel)

    // Character-related
    factoryOf<BaseDetailsScreenStateSlice<Character>>(::CharacterDetailsScreenStateSlice)
    factoryOf(::CharactersListScreenStateSlice)
    factoryOf(::RelatedCharactersSlice)
    factoryOf(::AllCharactersPagingSlice)
    factoryOf(::SingleCharacterSlice)
    singleOf(::CharacterTransformer)
    singleOf(::CharactersEntityRepository)
    viewModelOf(::CharacterDetailsViewModel)
    viewModelOf(::AllCharactersListViewModel)
    viewModelOf(::RelatedCharactersListViewModel)

    // Other transformers and singletons
    singleOf(::RelatedDatesTransformer)
    singleOf(::RelatedLinksTransformer)
    singleOf(::RelatedPricesTransformer)
    singleOf(::RelatedTextsTransformer)

}