package com.wongislandd.infinityindex.entities.comics.list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import com.wongislandd.infinityindex.infra.composables.GlobalTopAppBar
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.list.models.ComicsSortOption
import com.wongislandd.infinityindex.entities.comics.list.viewmodels.ComicsListViewModel
import com.wongislandd.infinityindex.entities.comics.util.ComicConstants
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun ComicsListScreen() {
    val viewModel = koinViewModel<ComicsListViewModel>()
    val screenState by viewModel.screenState.collectAsState()
    val lazyPagingComics = screenState.pagingData.collectAsLazyPagingItems()
    Scaffold(topBar = {
        GlobalTopAppBar(
            isTitleShown = !screenState.searchState.isSearchBoxVisible,
            showBackButton = false,
            actions = {
                ExpandingSearch(
                    isExpanded = screenState.searchState.isSearchBoxVisible,
                    currentSearchParam = screenState.searchState.searchQuery.text,
                    onSearchParamChanged = viewModel::setPendingSearchQuery,
                    onSearchParamSubmitted = viewModel::submitSearchQuery,
                    onSearchIconClicked = { viewModel.setSearchBoxVisibility(true) },
                )
                ComicsSortSelection(screenState.sortOption, viewModel::setSortOption)
            }
        )
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (screenState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                ComicsList(lazyPagingComics)
            }
        }
    }
}

@Composable
private fun ExpandingSearch(
    currentSearchParam: String,
    onSearchParamChanged: (String) -> Unit,
    onSearchParamSubmitted: (String) -> Unit,
    onSearchIconClicked: () -> Unit,
    isExpanded: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isExpanded) {
            TextField(
                value = currentSearchParam,
                onValueChange = onSearchParamChanged,
                placeholder = { Text("Search...") },
                singleLine = true,
                leadingIcon = {
                    IconButton(onClick = { onSearchParamSubmitted("") }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear Search",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = {
                        onSearchParamSubmitted(currentSearchParam)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowForward,
                            contentDescription = "Submit Search",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.onSurface,
                    backgroundColor = MaterialTheme.colors.surface,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    onSearchParamSubmitted(
                        currentSearchParam
                    )
                })
            )
        } else {
            IconButton(onClick = onSearchIconClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = if (currentSearchParam.isNotEmpty()) MaterialTheme.colors.secondary else MaterialTheme.colors.onPrimary,
                    contentDescription = "Search"
                )
            }
        }
    }
}

@Composable
private fun ComicsSortSelection(
    currentSortSelection: ComicsSortOption,
    onSortSelected: (ComicsSortOption) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        IconButton(
            onClick = { expanded = true },
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                tint = if (currentSortSelection == ComicConstants.DEFAULT_SORT_OPTION) {
                    MaterialTheme.colors.onPrimary
                } else {
                    MaterialTheme.colors.secondary
                },
                contentDescription = "Sort",
                modifier = Modifier.size(24.dp)
            )
        }
        DropdownMenu(
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
                    Text(
                        text = sortOption.displayName, color = if (currentSortSelection
                            == sortOption
                        ) MaterialTheme.colors.primary else Color.Unspecified
                    )
                }
            }
        }
    }
}

@Composable
private fun ComicsList(
    pagedComics: LazyPagingItems<Comic>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxSize()
    ) {

        items(pagedComics.itemCount, key= { index ->
            pagedComics[index]?.id ?: index
        }) { index ->
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

                        loadState.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && itemCount == 0 -> {
                            Text(text = "No results found")
                        }
                    }
                }
            }
        }
    }
}