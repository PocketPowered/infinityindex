package com.wongislandd.infinityindex.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.networking.models.NetworkComic
import com.wongislandd.infinityindex.util.Resource
import com.wongislandd.infinityindex.viewmodels.ComicsListViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ComicsListScreen() {
    val viewModel = koinViewModel<ComicsListViewModel>()
    val screenState by viewModel.screenState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (val state = screenState) {
            is Resource.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp).align(Alignment.Center),
                    strokeWidth = 1.dp,
                    color = MaterialTheme.colors.onSurface,
                )
            }
            is Resource.Error -> {
                Text("Error", color = MaterialTheme.colors.onSurface)
            }
            is Resource.Success -> {
                LazyColumn {
                    item {
                        Text(state.data.toString(), color = MaterialTheme.colors.onSurface)
                    }
                }
            }
        }
    }
}

@Composable
private fun ComicsList(comicsList: List<NetworkComic>) {
    LazyColumn {
        items(comicsList.size) { index ->
        }
    }
}