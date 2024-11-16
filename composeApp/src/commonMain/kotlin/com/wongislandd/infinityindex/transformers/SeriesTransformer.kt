package com.wongislandd.infinityindex.transformers

import com.wongislandd.infinityindex.entities.comics.transformers.DateTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.models.network.NetworkSeries
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.infra.models.DefaultImageType
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.navigation.RouteHelper
import com.wongislandd.infinityindex.infra.networking.models.getAvailableItems
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.infinityindex.infra.util.dropIfEmpty
import com.wongislandd.infinityindex.infra.util.safeLet

class SeriesTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
    private val datesTransformer: DateTransformer
) : DataWrapperTransformer<NetworkSeries, Series>() {
    override fun itemTransformer(input: NetworkSeries): Series? {
        return safeLet(
            input.id,
            input.title,
            input.modified
        ) { id, title, modified ->
            Series(
                id = id,
                displayName = title,
                navContext = NavigationContext(
                    RouteHelper.getSeriesDetailsRouteForId(id)
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
                lastModified = datesTransformer.transform(modified)
            )
        }
    }
}