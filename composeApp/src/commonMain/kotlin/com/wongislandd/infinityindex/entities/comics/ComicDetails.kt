package com.wongislandd.infinityindex.entities.comics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.infra.EntityDetails
import com.wongislandd.infinityindex.infra.composables.SimpleDetailsSection
import com.wongislandd.infinityindex.infra.composables.DetailsSection
import com.wongislandd.infinityindex.infra.composables.InformationSnippet

@Composable
fun ComicDetails(comic: Comic, modifier: Modifier = Modifier) {
    EntityDetails(comic, modifier = modifier) {
        SimpleDetailsSection("Description", comic.description)
        DetailsSection("Additional Information") {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                comic.relatedTexts.forEach { relatedText ->
                    InformationSnippet(relatedText.type.displayName, relatedText.text)
                }
                comic.pageCount?.let { pageCount ->
                    InformationSnippet("Page Count", pageCount.toString())
                }
                comic.format?.let { format ->
                    InformationSnippet("Format", format)
                }
                comic.relatedPrices.forEach { relatedPrice ->
                    InformationSnippet(relatedPrice.type.displayName, "$${relatedPrice.price}")
                }
                comic.relatedDates.forEach { relatedDates ->
                    InformationSnippet(relatedDates.type.displayName, relatedDates.date)
                }
            }
        }
//        comic.relatedLinks.forEach { relatedLink ->
//            InformationSnippet(relatedLink.type.displayName, relatedLink.url)
//        }
    }
}

