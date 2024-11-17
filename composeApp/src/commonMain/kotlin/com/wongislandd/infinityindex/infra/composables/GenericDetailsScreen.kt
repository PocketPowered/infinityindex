package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import com.wongislandd.infinityindex.infra.viewmodels.BaseDetailsViewModel
import com.wongislandd.infinityindex.infra.viewmodels.PagingDataConsumerScreenState
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
        GlobalTopAppBar()
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
                    Text(
                        text = "Error loading details",
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
    screenState: PagingDataConsumerScreenState,
    modifier: Modifier = Modifier
) {
    AdditionalDetailsLazyColumn(modifier = modifier.widthIn(max = 800.dp)) {
        item {
            PrimaryDetailContents(primaryModel, modifier = Modifier.padding(horizontal = 16.dp))
        }
        item {
            ListOfEntities(
                screenState,
                showAllRouteGetter = { entityType ->
                    NavigationHelper.getRelatedListRoute(
                        primaryModel.getEntityType(),
                        primaryModel.id,
                        entityType,
                        "${entityType.displayName} related to ${primaryModel.displayName}"
                    )
                },
            )
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