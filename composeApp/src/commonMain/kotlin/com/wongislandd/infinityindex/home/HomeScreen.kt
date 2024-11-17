package com.wongislandd.infinityindex.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.infra.composables.GlobalTopAppBar
import com.wongislandd.infinityindex.infra.composables.ListOfEntities
import com.wongislandd.infinityindex.infra.navigation.NavigationHelper
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<HomeViewModel>()
    val screenState by viewModel.screenStateSlice.screenState.collectAsState()
    val isLoaded by screenState.hasLoaded.collectAsState()
    Box(modifier = modifier.fillMaxSize()) {
        Scaffold(topBar = {
            GlobalTopAppBar(showBackButton = false)
        }) {
            LazyColumn(
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    ListOfEntities(screenState,
                        showAllRoute = {
                            NavigationHelper.getAllListRoute(it)
                        })
                }
            }
        }
    }
    AnimatedVisibility(
        modifier = Modifier.fillMaxSize().clickable { },
        visible = !isLoaded, exit = slideOutVertically(
            animationSpec = tween(
                durationMillis = 600, // Adjust duration for smoother effect
                easing = FastOutSlowInEasing // Smooth easing curve
            )
        )
    ) {
        InfinityIndexLoadingScreen()
    }
}