package com.wongislandd.infinityindex.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.infra.composables.GlobalTopAppBar
import com.wongislandd.infinityindex.infra.composables.ListOfEntities
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<HomeViewModel>()
    val screenState by viewModel.screenStateSlice.screenState.collectAsState()
    Scaffold(modifier = modifier, topBar = {
        GlobalTopAppBar()
    }) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                ListOfEntities(screenState, showAllEnabled = true)
            }
        }
    }
}