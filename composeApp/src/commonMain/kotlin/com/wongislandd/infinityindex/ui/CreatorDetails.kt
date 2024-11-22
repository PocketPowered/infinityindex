package com.wongislandd.infinityindex.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.infra.EntityDetails
import com.wongislandd.infinityindex.infra.composables.MarvelLinks
import com.wongislandd.infinityindex.models.local.Creator

@Composable
fun CreatorDetails(creator: Creator, modifier: Modifier = Modifier) {
    EntityDetails(creator, modifier = modifier) {
        MarvelLinks(creator.relatedLinks)
    }
}