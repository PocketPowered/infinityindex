package com.wongislandd.infinityindex.entities.creators

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.entities.comics.EntityDetails
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.infra.composables.TopLevelEntityDetails

@Composable
fun CreatorDetails(creator: Creator, modifier: Modifier = Modifier) {
    EntityDetails(modifier = modifier) {
        TopLevelEntityDetails(creator)
    }
}