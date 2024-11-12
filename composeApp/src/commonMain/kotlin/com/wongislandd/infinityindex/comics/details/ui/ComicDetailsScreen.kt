package com.wongislandd.infinityindex.comics.details.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.GlobalTopAppBar
import com.wongislandd.infinityindex.comics.details.viewmodels.ComicDetailsViewModel
import com.wongislandd.infinityindex.networking.util.Resource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ComicDetailsScreen(
    comicId: Int
) {
    val viewModel = koinViewModel<ComicDetailsViewModel>()
    LaunchedEffect(Unit) {
        viewModel.initialize(comicId)
    }
    val screenState by viewModel.screenState.collectAsState()
    Scaffold(topBar = {
        GlobalTopAppBar()
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (val comicRes = screenState.detailedComicRes) {
                is Resource.Success -> {
                    Text(
                        text = comicRes.data.title,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is Resource.Error -> {
                    Text(
                        text = "Error loading comic details",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}