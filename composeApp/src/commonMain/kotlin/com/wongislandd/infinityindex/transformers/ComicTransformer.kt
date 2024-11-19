package com.wongislandd.infinityindex.transformers

import com.wongislandd.infinityindex.entities.comics.models.TextType
import com.wongislandd.infinityindex.entities.comics.transformers.DateTransformer
import com.wongislandd.infinityindex.entities.comics.transformers.RelatedDatesTransformer
import com.wongislandd.infinityindex.entities.comics.transformers.RelatedLinksTransformer
import com.wongislandd.infinityindex.entities.comics.transformers.RelatedPricesTransformer
import com.wongislandd.infinityindex.entities.comics.transformers.RelatedTextsTransformer
import com.wongislandd.infinityindex.entities.comics.transformers.RoledCreatorOutput
import com.wongislandd.infinityindex.entities.comics.transformers.RoledCreatorTransformer
import com.wongislandd.infinityindex.infra.models.DefaultImageType
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.navigation.NavigationHelper
import com.wongislandd.infinityindex.infra.networking.models.getAvailableItems
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.dropIfEmpty
import com.wongislandd.infinityindex.infra.util.safeLet
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.network.NetworkComic

class ComicTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
    private val relatedDatesTransformer: RelatedDatesTransformer,
    private val relatedTextsTransformer: RelatedTextsTransformer,
    private val relatedLinksTransformer: RelatedLinksTransformer,
    private val relatedPricesTransformer: RelatedPricesTransformer,
    private val roledCreatorTransformer: RoledCreatorTransformer,
    private val datesTransformer: DateTransformer
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
        val creatorsOutput = input.creators?.let {
            roledCreatorTransformer.transform(it)
        } ?: RoledCreatorOutput(emptyMap(), emptyMap())

        // Sometimes description comes as empty but ISSUE_SOLICIT_TEXT is basically a description.
        // Pick one of these two, then drop ISSUE_SOLICIT_TEXT.
        val description =
            input.description.dropIfEmpty()
                ?: relatedTexts.find { it.type == TextType.ISSUE_SOLICIT_TEXT }?.text
        val filteredRelatedTexts = relatedTexts.filter { it.type != TextType.ISSUE_SOLICIT_TEXT }

        return safeLet(
            input.id,
            input.title,
            input.modified
        ) { id, title, modified ->
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
                    NavigationHelper.getDetailsRoute(
                        EntityType.COMICS,
                        id,
                        title
                    )
                ),
                pageCount = input.pageCount,
                issueNumber = input.issueNumber,
                lastModified = datesTransformer.transform(modified),
                relatedDates = relatedDates,
                relatedTexts = filteredRelatedTexts,
                relatedPrices = relatedPrices,
                relatedLinks = relatedLinks,
                variantDescription = input.variantDescription.dropIfEmpty(),
                description = description,
                upc = input.upc.dropIfEmpty(),
                diamondCode = input.diamondCode.dropIfEmpty(),
                ean = input.ean.dropIfEmpty(),
                issn = input.issn.dropIfEmpty(),
                format = input.format.dropIfEmpty(),
                relatedEventsCount = input.events.getAvailableItems(),
                relatedStoriesCount = input.stories.getAvailableItems(),
                relatedCharactersCount = input.characters.getAvailableItems(),
                relatedCreatorsCount = input.creators.getAvailableItems(),
                relatedSeriesCount = 0,
                relatedComicsCount = 0,
                comicCreatorsByRole = creatorsOutput.comicCreators,
                coverCreatorsByRole = creatorsOutput.coverCreators
            )
        }
    }
}