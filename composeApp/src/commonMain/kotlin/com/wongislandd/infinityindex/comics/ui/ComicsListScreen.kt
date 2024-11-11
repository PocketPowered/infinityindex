package com.wongislandd.infinityindex.comics.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import com.wongislandd.infinityindex.comics.models.ComicsSortOption
import com.wongislandd.infinityindex.comics.models.NetworkComic
import com.wongislandd.infinityindex.comics.viewmodels.ComicsListViewModel
import com.wongislandd.infinityindex.networking.util.getFullUrl
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun ComicsListScreen() {
    val viewModel = koinViewModel<ComicsListViewModel>()
    val sortOption by viewModel.sortOption.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val lazyPagingComics = viewModel.comicsResponse.collectAsLazyPagingItems()
    Column(modifier = Modifier.fillMaxSize()) {
        ComicsSortSelection(sortOption,
            onSortSelected = { sortOption ->
                viewModel.setSortOption(sortOption)
            })
        Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                ComicsList(lazyPagingComics)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ComicsSortSelection(
    currentSortSelection: ComicsSortOption,
    onSortSelected: (ComicsSortOption) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Row(modifier = modifier.background(MaterialTheme.colors.surface)) {
        Spacer(modifier = Modifier.weight(1f))
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                value = currentSortSelection.displayName,
                onValueChange = {},
                readOnly = true,
                label = { Text("Sort by") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                ComicsSortOption.entries.forEach { sortOption ->
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            onSortSelected(sortOption)
                        }
                    ) {
                        Text(text = sortOption.displayName)
                    }
                }
            }
        }
    }
}


@Composable
private fun ComicsList(
    pagedComics: LazyPagingItems<NetworkComic>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .background(Color.DarkGray)
            .fillMaxSize()
    ) {
        items(pagedComics.itemCount) { index ->
            pagedComics[index]?.let { comic ->
                ComicCard(comic)
            }
        }
        pagedComics.apply {
            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    when {
                        loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                            Text(text = "Error")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ComicCard(comic: NetworkComic, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth().height(500.dp),
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
                +ShimmerPlugin(
                    Shimmer.Flash(
                        baseColor = Color.White,
                        highlightColor = Color.LightGray,
                        duration = 650
                    )
                )
            },
            failure = {
                Text("Could not load image.")
            }
        )
    }
}