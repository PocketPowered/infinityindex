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
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import app.cash.paging.compose.LazyPagingItems
import com.wongislandd.infinityindex.infra.navigation.LocalNavHostController
import com.wongislandd.infinityindex.infra.util.DisplayableEntity
import com.wongislandd.infinityindex.infra.util.EntityType

@Composable
fun <T : DisplayableEntity> SectionedList(
    entityType: EntityType,
    pagedItems: LazyPagingItems<T>,
    itemKey: (T) -> Any? = { it.id },
    showAllNavRoute: String? = null,
) {
    if (pagedItems.itemCount == 0) {
        return
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        EntitySectionHeader(
            entityType = entityType,
            showAllNavRoute = showAllNavRoute,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                horizontal = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                count = pagedItems.itemCount,
                key = { index ->
                    pagedItems[index]?.let(itemKey) ?: index
                }
            ) { index ->
                pagedItems[index]?.let { item ->
                    EntityCard(
                        image = item.image,
                        title = item.displayName,
                        navContext = item.navContext
                    )
                }
            }
        }
    }
}

@Composable
private fun EntitySectionHeader(
    entityType: EntityType,
    showAllNavRoute: String?,
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
            text = entityType.displayName,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            modifier = modifier.padding(vertical = 8.dp)
        )
        showAllNavRoute?.also {
            Row(modifier = Modifier.clickable { navController.navigate(it) }) {
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