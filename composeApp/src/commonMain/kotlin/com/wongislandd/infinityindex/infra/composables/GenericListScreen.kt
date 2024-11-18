package com.wongislandd.infinityindex.infra.composables

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import com.wongislandd.infinityindex.ComicConstants
import com.wongislandd.infinityindex.infra.DetailsUiEvent
import com.wongislandd.infinityindex.infra.ListUiEvent
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.SelectableSortOption
import com.wongislandd.infinityindex.infra.util.SortOption
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.util.isDefaultSelectionSorted
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

data class RelatedEntityListConfiguration(
    val rootEntityType: EntityType,
    val rootEntityId: Int,
    val relatedEntityType: EntityType,
    val topBarTitle: String? = null
)

@OptIn(KoinExperimentalAPI::class)
@Composable
inline fun <NETWORK_TYPE, reified T : BaseListViewModel<NETWORK_TYPE, out EntityModel>> GenericListScreen(
    relatedListConfig: RelatedEntityListConfiguration? = null
) {
    val viewModel = koinViewModel<T>()

    // Signal to related plugins to look for these results
    relatedListConfig?.let {
        LaunchedEffect(relatedListConfig) {
            viewModel.uiEventBus.sendEvent(
                DetailsUiEvent.RelatedListInitialized(
                    relatedListConfig.rootEntityId,
                    relatedListConfig.rootEntityType
                )
            )
        }
    }

    val screenState by viewModel.screenStateSlice.screenState.collectAsState()
    val lazyPagingEntities = viewModel.screenStateSlice.listPagingData.collectAsLazyPagingItems()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = {
        GlobalTopAppBar(
            title = relatedListConfig?.topBarTitle
                ?: viewModel.screenStateSlice.entityType.displayName,
            isTitleShown = !screenState.searchState.isSearchBoxVisible,
            actions = {
                if (relatedListConfig == null) {
                    ExpandingSearch(
                        isExpanded = screenState.searchState.isSearchBoxVisible,
                        currentSearchParam = screenState.searchState.searchQuery.text,
                        onSearchCleared = {
                            coroutineScope.sendEvent(
                                viewModel.uiEventBus,
                                ListUiEvent.ClearSearchQuery
                            )
                        },
                        onSearchParamChanged = { newQuery ->
                            coroutineScope.sendEvent(
                                viewModel.uiEventBus,
                                ListUiEvent.SetPendingSearchQuery(newQuery)
                            )
                        },
                        onSearchParamSubmitted = {
                            coroutineScope.sendEvent(
                                viewModel.uiEventBus,
                                ListUiEvent.SubmitSearchQuery(it)
                            )
                        },
                        onSearchIconClicked = {
                            coroutineScope.sendEvent(
                                viewModel.uiEventBus,
                                ListUiEvent.SearchClicked
                            )
                        },
                    )
                    SortSelection(screenState.availableSortOptions, onSortSelected = {
                        coroutineScope.sendEvent(viewModel.uiEventBus, ListUiEvent.SortSelected(it))
                    })
                }
            }
        )
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            EntityList(lazyPagingEntities)
        }
    }
}

fun CoroutineScope.sendEvent(eventBus: EventBus<UiEvent>, event: ListUiEvent) {
    launch {
        eventBus.sendEvent(event)
    }
}

@Composable
fun ExpandingSearch(
    currentSearchParam: String,
    onSearchCleared: () -> Unit,
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
                    IconButton(onClick = onSearchCleared) {
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
fun SortSelection(
    sortOptions: List<SelectableSortOption>,
    onSortSelected: (SortOption) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        IconButton(
            onClick = { expanded = true },
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                tint = if (sortOptions.isDefaultSelectionSorted()) {
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
            sortOptions.forEach { selectableSortOption ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onSortSelected(selectableSortOption.sortOption)
                    }
                ) {
                    Text(
                        text = selectableSortOption.sortOption.displayName,
                        color = if (selectableSortOption.isSelected) MaterialTheme.colors.primary else Color.Unspecified
                    )
                }
            }
        }
    }
}

@Composable
fun EntityList(
    pagedEntities: LazyPagingItems<out EntityModel>,
    modifier: Modifier = Modifier
) {
    PagingWrapper(pagedEntities, modifier,
        itemContent = { entity ->
            EntityCard(entity)
        },
        placeholderContent = {
            GhostEntityCard()
        },
        errorContent = {
            Text("Error")
        },
        emptyContent = {
            Text("No results found")
        })
}

@Composable
private fun PagingWrapper(
    pagedEntities: LazyPagingItems<out EntityModel>,
    modifier: Modifier = Modifier,
    itemContent: @Composable (EntityModel) -> Unit,
    placeholderContent: @Composable () -> Unit,
    errorContent: @Composable () -> Unit,
    emptyContent: @Composable () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .background(MaterialTheme.colors.surface)
    ) {
        // Hide results if we are refreshing the whole source
        if (pagedEntities.loadState.refresh != LoadState.Loading) {
            items(pagedEntities.itemCount) { index ->
                pagedEntities[index]?.let { entity ->
                    itemContent(entity)
                }
            }
        }
        pagedEntities.apply {
            if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading) {
                repeat(ComicConstants.LIST_PAGE_SIZE * 2) {
                    item {
                        placeholderContent()
                    }
                }
            }
            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    when {
                        loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                            errorContent()
                        }

                        loadState.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && itemCount == 0 -> {
                            emptyContent()
                        }
                    }
                }
            }
        }
    }
}