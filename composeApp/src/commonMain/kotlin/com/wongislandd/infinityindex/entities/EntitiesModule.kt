package com.wongislandd.infinityindex.entities

import com.wongislandd.infinityindex.entities.characters.CharacterDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.characters.CharacterDetailsViewModel
import com.wongislandd.infinityindex.viewmodels.shared.ComicDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.comics.ComicDetailsViewModel
import com.wongislandd.infinityindex.entities.comics.transformers.RelatedDatesTransformer
import com.wongislandd.infinityindex.entities.comics.transformers.RelatedLinksTransformer
import com.wongislandd.infinityindex.entities.comics.transformers.RelatedPricesTransformer
import com.wongislandd.infinityindex.entities.comics.transformers.RelatedTextsTransformer
import com.wongislandd.infinityindex.viewmodels.shared.CreatorDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.creators.CreatorDetailsViewModel
import com.wongislandd.infinityindex.viewmodels.shared.EventDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.events.EventDetailsViewModel
import com.wongislandd.infinityindex.viewmodels.shared.SeriesDetailsScreenStateSlice
import com.wongislandd.infinityindex.entities.series.SeriesDetailsViewModel
import com.wongislandd.infinityindex.viewmodels.shared.StoriesListScreenStateSlice
import com.wongislandd.infinityindex.entities.stories.StoryDetailsViewModel
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.shared.ComicsListScreenStateSlice
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
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedCharactersListViewModel
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedCharactersSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedComicsListViewModel
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedComicsSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedCreatorsListViewModel
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedCreatorsSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedEventsListViewModel
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedEventsSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedSeriesListViewModel
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedSeriesSlice
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedStoriesListViewModel
import com.wongislandd.infinityindex.viewmodels.relatedlist.RelatedStoriesSlice
import com.wongislandd.infinityindex.viewmodels.rootlist.AllCharactersListViewModel
import com.wongislandd.infinityindex.viewmodels.rootlist.AllCharactersPagingSlice
import com.wongislandd.infinityindex.viewmodels.rootlist.AllComicsListViewModel
import com.wongislandd.infinityindex.viewmodels.rootlist.AllComicsPagingSlice
import com.wongislandd.infinityindex.viewmodels.rootlist.AllCreatorsListViewModel
import com.wongislandd.infinityindex.viewmodels.rootlist.AllCreatorsPagingSlice
import com.wongislandd.infinityindex.viewmodels.rootlist.AllEventsListViewModel
import com.wongislandd.infinityindex.viewmodels.rootlist.AllEventsPagingSlice
import com.wongislandd.infinityindex.viewmodels.rootlist.AllSeriesListViewModel
import com.wongislandd.infinityindex.viewmodels.rootlist.AllSeriesPagingSlice
import com.wongislandd.infinityindex.viewmodels.rootlist.AllStoriesListViewModel
import com.wongislandd.infinityindex.viewmodels.rootlist.AllStoriesPagingSlice
import com.wongislandd.infinityindex.viewmodels.shared.CharactersListScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.shared.CreatorsListScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.shared.EventsListScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.shared.SeriesListScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.shared.StoryDetailsScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.single.SingleCharacterSlice
import com.wongislandd.infinityindex.viewmodels.single.SingleComicSlice
import com.wongislandd.infinityindex.viewmodels.single.SingleCreatorSlice
import com.wongislandd.infinityindex.viewmodels.single.SingleEventSlice
import com.wongislandd.infinityindex.viewmodels.single.SingleSeriesSlice
import com.wongislandd.infinityindex.viewmodels.single.SingleStorySlice
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