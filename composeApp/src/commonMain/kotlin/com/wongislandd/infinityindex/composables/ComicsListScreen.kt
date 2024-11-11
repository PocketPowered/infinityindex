package com.wongislandd.infinityindex.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
            .background(Color.DarkGray)
            .fillMaxSize()
    ) {
        when (val state = screenState) {
            is Resource.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp).align(Alignment.Center),
                    strokeWidth = 1.dp,
                    color = Color.White,
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
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(comicsList.size) { index ->
            ComicCard(comicsList[index])
        }
    }
}

@Composable
private fun ComicCard(comic: NetworkComic, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth().height(500.dp)
            .background(Color.DarkGray)
    ) {
        Column {
//            // invalidate these, should never make it to UI if these are missing
            comic.thumbnail?.getFullUrl()?.also { url ->
                ComicImage(url, modifier = Modifier.height(300.dp))
            }
            comic.title?.also {
                ComicTitlePlate(it)
            }
        }
    }
}

@Composable
private fun ComicTitlePlate(title: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun ComicImage(url: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(Color.Black).fillMaxWidth()) {
        CoilImage(
            imageModel = { url },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Inside,
                alignment = Alignment.Center
            ),
            modifier = Modifier.align(Alignment.Center),
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
}