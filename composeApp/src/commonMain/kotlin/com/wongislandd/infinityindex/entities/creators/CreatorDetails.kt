package com.wongislandd.infinityindex.entities.creators

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.infra.composables.TopLevelEntityDetails

@Composable
fun CreatorDetails(creator: Creator, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        TopLevelEntityDetails(creator)
    }
}