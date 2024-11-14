package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import com.wongislandd.infinityindex.infra.util.DisplayableEntity

@Composable
fun <T : DisplayableEntity> SectionedList(
    title: String,
    pagedItems: LazyPagingItems<T>,
    itemKey: (T) -> Any? = { it.id }
) {
    if (pagedItems.itemCount == 0) {
        return
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        SectionHeader(text = title, modifier = Modifier.padding(horizontal = 16.dp))
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
                        title = item.displayName
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Start,
        modifier = modifier.padding(vertical = 8.dp)
    )
}