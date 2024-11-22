package com.wongislandd.infinityindex.transformers

import com.wongislandd.infinityindex.transformers.util.DateTransformer
import com.wongislandd.infinityindex.transformers.util.RelatedLinksTransformer
import com.wongislandd.infinityindex.infra.models.DefaultImageType
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.navigation.NavigationHelper
import com.wongislandd.infinityindex.infra.networking.models.getAvailableItems
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.safeLet
import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.models.network.NetworkCreator

class CreatorTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
    private val dateTransformer: DateTransformer,
    private val relatedLinksTransformer: RelatedLinksTransformer
) : DataWrapperTransformer<NetworkCreator, Creator>() {
    override fun itemTransformer(input: NetworkCreator): Creator? {
        val relatedLinks = input.urls?.let {
            relatedLinksTransformer.transform(it)
        } ?: emptyList()
        return safeLet(
            input.id,
            input.fullName,
            input.modified?.let { dateTransformer.transform(it) }
        ) { id, name, modified ->
            Creator(
                id = id,
                displayName = name,
                navContext = NavigationContext(
                    NavigationHelper.getDetailsRoute(
                        EntityType.CREATORS,
                        id,
                        name
                    )
                ),
                image = loadableImageTransformer.transform(
                    LoadableImageTransformerInput(
                        networkImage = input.thumbnail,
                        defaultImageType = DefaultImageType.PERSON
                    ),
                ),
                relatedEventsCount = input.events.getAvailableItems(),
                relatedStoriesCount = input.stories.getAvailableItems(),
                relatedCharactersCount = 0,
                relatedCreatorsCount = 0,
                relatedSeriesCount = input.series.getAvailableItems(),
                relatedComicsCount = input.comics.getAvailableItems(),
                lastModified = modified,
                relatedLinks = relatedLinks
            )
        }
    }
}