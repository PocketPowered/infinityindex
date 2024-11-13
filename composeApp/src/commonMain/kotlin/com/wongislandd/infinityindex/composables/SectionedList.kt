package com.wongislandd.infinityindex.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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

@Composable
fun <T : Any> SectionedList(
    title: String,
    items: LazyPagingItems<T>,
    itemKey: (T) -> Any?,
    itemContent: @Composable (T) -> Unit
) {
    if (items.itemCount == 0) {
        return
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        SectionHeader(text = title)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                count = items.itemCount,
                key = { index ->
                    items[index]?.let(itemKey) ?: index
                }
            ) { index ->
                items[index]?.let { item ->
                    itemContent(item)
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