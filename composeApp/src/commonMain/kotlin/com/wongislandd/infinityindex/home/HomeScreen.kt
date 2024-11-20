package com.wongislandd.infinityindex.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.infra.composables.EntitiesListUseCase
import com.wongislandd.infinityindex.infra.composables.GenericErrorScreen
import com.wongislandd.infinityindex.infra.composables.GlobalTopAppBar
import com.wongislandd.infinityindex.infra.composables.ListOfEntities
import com.wongislandd.infinityindex.infra.composables.MarvelAttributionTextLabel
import com.wongislandd.infinityindex.infra.navigation.NavigationHelper
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<HomeViewModel>()
    val screenState by viewModel.screenStateSlice.screenState.collectAsState()
    Box(modifier = modifier.fillMaxSize()) {
        Scaffold(topBar = {
            GlobalTopAppBar(showBackButton = false)
        }) {
            if (screenState.errorData != null) {
                GenericErrorScreen(
                    errorMessage = screenState.errorData.toString(),
                    modifier = Modifier.fillMaxSize().align(Alignment.Center)
                )
            } else {
                LazyColumn {
                    item {
                        ListOfEntities(
                            screenState,
                            showAllRouteGetter = { entityType ->
                                NavigationHelper.getAllListRoute(entityType)
                            },
                            useCase = EntitiesListUseCase.HOME
                        )
                    }
                    item {
                        MarvelAttributionTextLabel()
                    }
                }
            }
        }
        if (screenState.isHomeScreenLoading) {
            InfinityIndexLoadingScreen(modifier = Modifier.clickable(
                indication = null, // Removes ripple or any click feedback
                interactionSource = remember { MutableInteractionSource() }
            ) { })
        }
    }
}