package com.wongislandd.infinityindex.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.infra.EntityDetails
import com.wongislandd.infinityindex.infra.composables.InformationSnippet
import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.infra.composables.DetailsSection
import com.wongislandd.infinityindex.infra.composables.SimpleDetailsSection
import com.wongislandd.infinityindex.infra.util.safeLet

@Composable
fun EventDetails(event: Event, modifier: Modifier = Modifier) {
    EntityDetails(event, modifier = modifier) {
        SimpleDetailsSection("Description", event.description)
        DetailsSection("Additional Information") {
            safeLet(event.start, event.end) { start, end ->
                InformationSnippet("Active", "$start - $end")
            }
        }
    }
}