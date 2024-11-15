package com.wongislandd.infinityindex.entities.events

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.infra.composables.TopLevelEntityDetails

@Composable
fun EventDetails(event: Event, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        TopLevelEntityDetails(event)
    }
}