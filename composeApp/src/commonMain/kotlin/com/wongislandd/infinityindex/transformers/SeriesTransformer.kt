package com.wongislandd.infinityindex.transformers

import com.wongislandd.infinityindex.transformers.util.DateTransformer
import com.wongislandd.infinityindex.transformers.util.RoledCreatorOutput
import com.wongislandd.infinityindex.transformers.util.RoledCreatorTransformer
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
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.models.network.NetworkSeries

class SeriesTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
    private val datesTransformer: DateTransformer,
    private val roledCreatorTransformer: RoledCreatorTransformer
) : DataWrapperTransformer<NetworkSeries, Series>() {
    override fun itemTransformer(input: NetworkSeries): Series? {
        val creatorsOutput = input.creators?.let {
            roledCreatorTransformer.transform(it)
        } ?: RoledCreatorOutput(emptyMap(), emptyMap())
        return safeLet(
            input.id,
            input.title,
            input.modified?.let { datesTransformer.transform(it) }
        ) { id, title, modified ->
            Series(
                id = id,
                displayName = title,
                navContext = NavigationContext(
                    NavigationHelper.getDetailsRoute(
                        EntityType.SERIES,
                        id,
                        title
                    )
                ),
                image = loadableImageTransformer.transform(
                    LoadableImageTransformerInput(
                        networkImage = input.thumbnail,
                        defaultImageType = DefaultImageType.BOOK
                    )
                ),
                description = input.description.dropIfEmpty(),
                rating = input.rating.dropIfEmpty(),
                startYear = input.startYear,
                endYear = input.endYear,
                relatedEventsCount = input.events.getAvailableItems(),
                relatedStoriesCount = input.stories.getAvailableItems(),
                relatedCharactersCount = input.characters.getAvailableItems(),
                relatedCreatorsCount = input.creators.getAvailableItems(),
                relatedSeriesCount = 0,
                relatedComicsCount = input.comics.getAvailableItems(),
                lastModified = modified,
                creatorsByRole = creatorsOutput.primaryCreators,
                type = input.type.dropIfEmpty()
            )
        }
    }
}