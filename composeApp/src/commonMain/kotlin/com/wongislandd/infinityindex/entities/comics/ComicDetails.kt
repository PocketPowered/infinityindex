package com.wongislandd.infinityindex.entities.comics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.infra.composables.SimpleDetailsSection
import com.wongislandd.infinityindex.infra.composables.DetailsSection
import com.wongislandd.infinityindex.infra.composables.TopLevelEntityDetails

@Composable
fun ComicDetails(comic: Comic, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TopLevelEntityDetails(comic)
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
                    InformationSnippet(relatedPrice.type.displayName, relatedPrice.price.toString())
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

@Composable
fun InformationSnippet(name: String, value: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(text = "$name:", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.widthIn(max=200.dp)
        )
    }
}