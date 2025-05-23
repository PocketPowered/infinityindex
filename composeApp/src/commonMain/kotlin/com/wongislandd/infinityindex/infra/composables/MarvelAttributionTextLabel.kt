package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.infra.viewmodels.AppLeveled


@Composable
fun MarvelAttributionTextLabel(modifier: Modifier = Modifier) {
    val attributionTextLabel = AppLeveled.attributionText.collectAsState(null)
    attributionTextLabel.value?.also {
        Box(modifier = modifier.fillMaxSize().padding(16.dp)) {
            Text(
                text = it,
                style = MaterialTheme.typography.caption,
                fontWeight = MaterialTheme.typography.caption.fontWeight,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}