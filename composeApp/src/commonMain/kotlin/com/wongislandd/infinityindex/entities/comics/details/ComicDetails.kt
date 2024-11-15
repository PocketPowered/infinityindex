package com.wongislandd.infinityindex.entities.comics.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.infra.composables.MarvelImage
import com.wongislandd.infinityindex.infra.composables.TopLevelEntityDetails

@Composable
fun ComicDetails(comic: Comic, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        TopLevelEntityDetails(comic)
    }
}