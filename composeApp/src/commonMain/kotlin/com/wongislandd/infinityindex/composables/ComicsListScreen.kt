package com.wongislandd.infinityindex.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import com.wongislandd.infinityindex.networking.models.NetworkComic
import com.wongislandd.infinityindex.util.Resource
import com.wongislandd.infinityindex.util.getFullUrl
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
                state.data.data?.results?.also {
                    ComicsList(state.data.data.results)
                }
            }
        }
    }
}

@Composable
private fun ComicsList(comicsList: List<NetworkComic>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(comicsList.size) { index ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    comicsList[index].thumbnail?.getFullUrl()?.also { url ->
                        ComicImage(url)
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        comicsList[index].title ?: "No title",
                        color = MaterialTheme.colors.onSurface, style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun ComicImage(url: String) {
    CoilImage(
        imageModel = { url },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Inside,
            alignment = Alignment.Center
        ),
        modifier = Modifier.fillMaxSize(),
        component = rememberImageComponent {
            ShimmerPlugin(
                Shimmer.Flash(
                    baseColor = Color.White,
                    highlightColor = Color.LightGray,
                    duration = 1000
                )
            )
        },
        failure = {
            Text("Could not load image.")
        }
    )
}