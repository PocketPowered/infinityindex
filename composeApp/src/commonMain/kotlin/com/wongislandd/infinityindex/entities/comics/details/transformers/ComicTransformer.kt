package com.wongislandd.infinityindex.entities.comics.details.transformers

import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.infra.models.DefaultImageType
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.navigation.RouteHelper
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.infra.networking.models.hasItems
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.infinityindex.infra.util.dropIfEmpty
import com.wongislandd.infinityindex.infra.util.safeLet

class ComicTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
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
            input.title,
        ) { id, title ->
            Comic(
                id = id,
                displayName = title,
                image = loadableImageTransformer.transform(
                    LoadableImageTransformerInput(
                        networkImage = input.thumbnail,
                        defaultImageType = DefaultImageType.BOOK
                    )
                ),
                navContext = NavigationContext(
                    RouteHelper.getComicDetailsRouteForId(id)
                ),
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
                hasCreators = input.creators.hasItems(),
                hasSeries = false,
                hasComics = false
            )
        }
    }
}