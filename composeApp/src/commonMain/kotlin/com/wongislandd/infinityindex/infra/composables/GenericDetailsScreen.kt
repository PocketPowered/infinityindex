package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.entities.characters.CharacterDetails
import com.wongislandd.infinityindex.entities.comics.ComicDetails
import com.wongislandd.infinityindex.entities.creators.CreatorDetails
import com.wongislandd.infinityindex.entities.events.EventDetails
import com.wongislandd.infinityindex.entities.series.SeriesDetails
import com.wongislandd.infinityindex.entities.stories.StoryDetails
import com.wongislandd.infinityindex.infra.DetailsUiEvent
import com.wongislandd.infinityindex.infra.navigation.NavigationHelper
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.Resource
import com.wongislandd.infinityindex.infra.util.getEntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsScreenState
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.models.local.Story
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
inline fun <reified T : BaseDetailsViewModel<out EntityModel>> GenericDetailsScreen(
    primaryId: Int,
    title: String?,
    modifier: Modifier = Modifier,
) {
    val viewModel = koinViewModel<T>()
    LaunchedEffect(Unit) {
        viewModel.uiEventBus.sendEvent(
            DetailsUiEvent.PageInitialized(
                primaryId,
                viewModel.entityType
            )
        )
    }
    val screenState by viewModel.screenStateSlice.screenState.collectAsState()
    Scaffold(modifier = modifier, topBar = {
        GlobalTopAppBar(
            title = title
        )
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (val primaryRes = screenState.primaryRes) {
                is Resource.Success -> {
                    AdditionalDetailsContents(
                        primaryRes.data,
                        screenState,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is Resource.Error -> {
                    GenericErrorScreen(
                        errorMessage = primaryRes.error?.displayMessage ?: "Error loading details",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun AdditionalDetailsContents(
    primaryModel: EntityModel,
    screenState: BaseDetailsScreenState<out EntityModel>,
    modifier: Modifier = Modifier
) {
    val supplementaryData = screenState.supplementaryData.collectAsState()
    val supplementaryDataValue = supplementaryData.value
    AdditionalDetailsLazyColumn(modifier = modifier.widthIn(max = 1000.dp)) {
        item {
            PrimaryDetailContents(primaryModel, modifier = Modifier.padding(horizontal = 16.dp))
        }
        // Support showing supplementary data
        supplementaryData.value?.also { supplementaryModel ->
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    EntitySectionHeader(
                        entityType = supplementaryModel.getEntityType(),
                        totalEntityCount = null,
                        showAllRoute = null,
                    )
                    SupplementaryDetailContents(supplementaryModel)
                }
            }
        }
        item {
            ListOfEntities(
                screenState,
                showAllRouteGetter = { entityType ->
                    // Show all related to the supplementary data (useful for comics showing on comics screen from series resolution)
                    if (supplementaryDataValue != null && entityType == primaryModel.getEntityType()) {
                        NavigationHelper.getRelatedListRoute(
                            supplementaryDataValue.getEntityType(),
                            supplementaryDataValue.id,
                            entityType,
                            "${entityType.displayName} related to ${supplementaryDataValue.displayName}"
                        )
                    } else {
                        NavigationHelper.getRelatedListRoute(
                            primaryModel.getEntityType(),
                            primaryModel.id,
                            entityType,
                            "${entityType.displayName} related to ${primaryModel.displayName}"
                        )
                    }
                },
                useCase = EntitiesListUseCase.DETAILS,
            )
        }
        item {
            BottomDetailContents(primaryModel)
        }
        item {
            MarvelAttributionTextLabel()
        }
    }
}

@Composable
private fun PrimaryDetailContents(primaryModel: EntityModel, modifier: Modifier = Modifier) {
    when (primaryModel) {
        is Comic -> {
            ComicDetails(primaryModel, modifier)
        }

        is Creator -> {
            CreatorDetails(primaryModel, modifier)
        }

        is Character -> {
            CharacterDetails(primaryModel, modifier)
        }

        is Story -> {
            StoryDetails(primaryModel, modifier)
        }

        is Series -> {
            SeriesDetails(primaryModel, modifier)
        }

        is Event -> {
            EventDetails(primaryModel, modifier)
        }
    }
}

@Composable
private fun SupplementaryDetailContents(
    supplementaryEntityModel: EntityModel,
    modifier: Modifier = Modifier
) {
    EntityCard(
        entity = supplementaryEntityModel,
        modifier = modifier
    )
}

@Composable
private fun BottomDetailContents(
    primaryModel: EntityModel, modifier: Modifier = Modifier
) {
    when (primaryModel) {
        is Comic -> {
            MarvelLinks(primaryModel.relatedLinks, modifier)
        }

        is Character -> {
            MarvelLinks(primaryModel.relatedLinks, modifier)
        }
    }
}