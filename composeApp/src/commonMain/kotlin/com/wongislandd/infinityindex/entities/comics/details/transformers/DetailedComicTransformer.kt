package com.wongislandd.infinityindex.entities.comics.details.transformers

import com.wongislandd.infinityindex.util.DataWrapperTransformer
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.networking.util.ImageUrlTransformer
import com.wongislandd.infinityindex.networking.util.hasItems
import com.wongislandd.infinityindex.util.safeLet

class DetailedComicTransformer(
    private val imageUrlTransformer: ImageUrlTransformer,
    private val relatedDatesTransformer: RelatedDatesTransformer,
    private val relatedTextsTransformer: RelatedTextsTransformer,
    private val relatedLinksTransformer: RelatedLinksTransformer,
    private val relatedPricesTransformer: RelatedPricesTransformer
) : DataWrapperTransformer<NetworkComic, Comic>() {

    override fun itemTransformer(input: NetworkComic): Comic? {
        val relatedDates = input.dates?.let {
            relatedDatesTransformer.transform(it)
        } ?: emptyList()
        val relatedTexts = input.textObjects?.let {
            relatedTextsTransformer.transform(it)
        } ?: emptyList()
        val relatedLinks = input.urls?.let {
            relatedLinksTransformer.transform(it)
        } ?: emptyList()
        val relatedPrices = input.prices?.let {
            relatedPricesTransformer.transform(it)
        } ?: emptyList()

        return safeLet(
            input.id,
            input.thumbnail,
            input.title,
        ) { id, thumbnail, title ->
            Comic(
                id = id,
                title = title,
                imageUrl = imageUrlTransformer.transform(thumbnail),
                pageCount = input.pageCount,
                issueNumber = input.issueNumber,
                lastModified = input.modified.dropIfEmpty(),
                relatedDates = relatedDates,
                relatedTexts = relatedTexts,
                relatedPrices = relatedPrices,
                relatedLinks = relatedLinks,
                variantDescription = input.variantDescription.dropIfEmpty(),
                description = input.description.dropIfEmpty(),
                upc = input.upc.dropIfEmpty(),
                diamondCode = input.diamondCode.dropIfEmpty(),
                ean = input.ean.dropIfEmpty(),
                issn = input.issn.dropIfEmpty(),
                format = input.format.dropIfEmpty(),
                hasEvents = input.events.hasItems(),
                hasStories = input.stories.hasItems(),
                hasCharacters = input.characters.hasItems(),
                hasCreators = input.creators.hasItems()
            )
        }
    }
}

private fun String?.dropIfEmpty() = this?.takeIf { it.isNotBlank() }