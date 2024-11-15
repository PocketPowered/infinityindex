package com.wongislandd.infinityindex.entities.creators

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.infra.EntityDetails
import com.wongislandd.infinityindex.entities.creators.models.Creator

@Composable
fun CreatorDetails(creator: Creator, modifier: Modifier = Modifier) {
    EntityDetails(creator, modifier = modifier) {}
}