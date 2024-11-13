package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun EntityCard(imageUrl: String, title: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .wrapContentHeight(),
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) { // Make the Column fill the width
            MarvelImage(imageUrl = imageUrl,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(150.dp))
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}