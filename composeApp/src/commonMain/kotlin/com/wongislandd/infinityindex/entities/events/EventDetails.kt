package com.wongislandd.infinityindex.entities.events

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.entities.comics.EntityDetails
import com.wongislandd.infinityindex.entities.comics.InformationSnippet
import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.infra.composables.DetailsSection
import com.wongislandd.infinityindex.infra.composables.SimpleDetailsSection
import com.wongislandd.infinityindex.infra.composables.TopLevelEntityDetails
import com.wongislandd.infinityindex.infra.util.safeLet

@Composable
fun EventDetails(event: Event, modifier: Modifier = Modifier) {
    EntityDetails(modifier = modifier) {
        TopLevelEntityDetails(event)
        SimpleDetailsSection("Description", event.description)
        DetailsSection("Additional Information") {
            safeLet(event.start, event.end) { start, end ->
                InformationSnippet("Active: ", "$start - $end")
            }
        }
    }
}