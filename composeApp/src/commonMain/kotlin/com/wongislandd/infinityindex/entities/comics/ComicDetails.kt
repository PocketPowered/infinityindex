package com.wongislandd.infinityindex.entities.comics

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.infra.EntityDetails
import com.wongislandd.infinityindex.infra.composables.DetailsSection
import com.wongislandd.infinityindex.infra.composables.InformationSnippet
import com.wongislandd.infinityindex.infra.composables.SimpleDetailsSection
import com.wongislandd.infinityindex.models.local.Comic

@Composable
fun ComicDetails(comic: Comic, modifier: Modifier = Modifier) {
    EntityDetails(comic, modifier = modifier) {
        SimpleDetailsSection("Description", comic.description)
        ComicCreatorsSection(comic.comicCreatorsByRole, comic.coverCreatorsByRole)
        DetailsSection("Relevant Dates") {
            comic.relatedDates.forEach { relatedDates ->
                InformationSnippet(relatedDates.type.displayName, relatedDates.date)
            }
        }
        DetailsSection("Additional Information") {
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
                InformationSnippet(relatedPrice.type.displayName, relatedPrice.price)
            }
        }
    }
}

@Composable
private fun ComicCreatorsSection(
    comicCreatorsByRole: Map<String, List<String>>,
    coverCreatorsByRole: Map<String, List<String>>,
    modifier: Modifier = Modifier
) {
    if (comicCreatorsByRole.isNotEmpty() || coverCreatorsByRole.isNotEmpty()) {
        DetailsSection("Creators", modifier = modifier) {
            comicCreatorsByRole.forEach { (role, creators) ->
                InformationSnippet(role, creators.joinToString())
            }
            coverCreatorsByRole.forEach { (role, creators) ->
                InformationSnippet(role, creators.joinToString())
            }
        }
    }
}
