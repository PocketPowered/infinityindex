package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import com.wongislandd.infinityindex.ComicConstants
import com.wongislandd.infinityindex.infra.navigation.LocalNavHostController
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.EntityType
import kotlin.math.min

@Composable
fun <T : EntityModel> SectionedEntityList(
    entityType: EntityType,
    totalItemCount: Long,
    pagedItems: LazyPagingItems<T>,
    showAllRoute: String,
    useCase: EntitiesListUseCase
) {
    val navController = LocalNavHostController.current
    val isShowAllAvailable = totalItemCount > ComicConstants.RELATED_DETAILS_MAX_ENTITY_RESULTS
    if (!pagedItems.loadState.isInitializing() && pagedItems.itemCount == 0) {
        return
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        EntitySectionHeader(
            entityType = entityType,
            totalEntityCount = totalItemCount,
            showAllRoute = showAllRoute.takeIf { isShowAllAvailable && useCase == EntitiesListUseCase.GENERAL },
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                horizontal = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(
                count = pagedItems.itemCount
            ) { index ->
                pagedItems[index]?.let { entity ->
                    EntityCard(entity)
                }
            }
            pagedItems.apply {
                when {
                    // Placeholder cards
                    loadState.isInitializing() -> {
                        val numGhostCards = min(
                            totalItemCount.toInt(),
                            ComicConstants.RELATED_DETAILS_MAX_ENTITY_RESULTS
                        )
                        repeat(
                            numGhostCards
                        ) {
                            item {
                                GhostEntityCard()
                            }
                        }
                    }

                    loadState.isDoneLoading() -> {
                        // If there are more results to show
                        if (isShowAllAvailable)
                            item {
                                SeeMoreEntityCard(modifier = Modifier
                                    .clickable {
                                        navController.navigate(
                                            showAllRoute
                                        )
                                    })
                            }
                    }

                    loadState.append == LoadState.Loading -> {
                        item {
                            CircularProgressIndicator()
                        }
                    }

                }
            }
        }
    }
}

@Composable
private fun EntitySectionHeader(
    entityType: EntityType,
    totalEntityCount: Long,
    showAllRoute: String?,
    modifier: Modifier = Modifier
) {
    val navController = LocalNavHostController.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${entityType.displayName} ($totalEntityCount)",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            modifier = modifier.padding(vertical = 8.dp)
        )
        showAllRoute?.also {
            Row(modifier = Modifier.clickable {
                navController.navigate(
                    showAllRoute
                )
            }) {
                Text(text = "See all", fontWeight = FontWeight.Thin)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "See all"
                )
            }
        }
    }
}

private fun CombinedLoadStates.isInitializing() = this.refresh == LoadState.Loading

private fun CombinedLoadStates.isDoneLoading() =
    this.refresh is LoadState.NotLoading && this.append.endOfPaginationReached