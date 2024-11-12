package com.wongislandd.infinityindex.pillars.comics.details.transformers

import com.wongislandd.infinityindex.pillars.comics.details.models.DetailedComic
import com.wongislandd.infinityindex.pillars.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.pillars.comics.list.transformers.ImageUrlTransformer
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

class DetailedComicTransformer(
    private val imageUrlTransformer: ImageUrlTransformer,
    private val relatedDatesTransformer: RelatedDatesTransformer,
    private val relatedTextsTransformer: RelatedTextsTransformer,
    private val relatedLinksTransformer: RelatedLinksTransformer,
    private val relatedPricesTransformer: RelatedPricesTransformer
) : Transformer<NetworkComic, DetailedComic> {

    override fun transform(input: NetworkComic): DetailedComic? {
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
            input.thumbnail,
            input.title,
        ) { thumbnail, title ->
            DetailedComic(
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
                format = input.format.dropIfEmpty()
            )
        }
    }
}

private fun String?.dropIfEmpty() = this?.takeIf { it.isNotBlank() }