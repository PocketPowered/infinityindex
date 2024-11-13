package com.wongislandd.infinityindex.pillars.comics.details.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import com.wongislandd.infinityindex.GlobalTopAppBar
import com.wongislandd.infinityindex.networking.util.Resource
import com.wongislandd.infinityindex.pillars.comics.details.models.Comic
import com.wongislandd.infinityindex.pillars.comics.details.viewmodels.ComicDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ComicDetailsScreen(
    comicId: Int
) {
    val viewModel = koinViewModel<ComicDetailsViewModel>()
    LaunchedEffect(Unit) {
        viewModel.uiEventBus.sendEvent(ComicDetailsUiEvent.PageInitialized(comicId))
    }
    val screenState by viewModel.comicDetailsScreenStateSlice.screenState.collectAsState()
    Scaffold(topBar = {
        GlobalTopAppBar()
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (val comicRes = screenState.comicRes) {
                is Resource.Success -> {
                    ComicDetails(comicRes.data, modifier = Modifier.align(Alignment.Center).fillMaxWidth(.8f))
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

@Composable
private fun ComicDetails(comic: Comic, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            FullScreenComicImage(imageUrl = comic.imageUrl)
        }
        item {
            Text(
                text = comic.title,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun FullScreenComicImage(imageUrl: String) {
    CoilImage(
        imageModel = { imageUrl },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Inside,
            alignment = Alignment.Center
        ),
        component = rememberImageComponent {
            +ShimmerPlugin()
        },
        failure = {
            Text("Could not load image.")
        }
    )
}