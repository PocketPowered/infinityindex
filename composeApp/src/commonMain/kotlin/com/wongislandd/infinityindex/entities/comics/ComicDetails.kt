package com.wongislandd.infinityindex.entities.comics

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.infra.composables.DetailsSection
import com.wongislandd.infinityindex.infra.composables.TopLevelEntityDetails

@Composable
fun ComicDetails(comic: Comic, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        TopLevelEntityDetails(comic)
        DetailsSection("Description", comic.description)
    }
}