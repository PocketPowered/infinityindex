package com.wongislandd.infinityindex.entities.series

import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.entities.series.models.NetworkSeries
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.infra.models.DefaultImageType
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.navigation.RouteHelper
import com.wongislandd.infinityindex.infra.networking.models.hasItems
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.infinityindex.infra.util.safeLet

class SeriesTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
) : DataWrapperTransformer<NetworkSeries, Series>() {
    override fun itemTransformer(input: NetworkSeries): Series? {
        return safeLet(
            input.id,
            input.title,
        ) { id, title ->
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
                description = input.description,
                rating = input.rating,
                startYear = input.startYear,
                endYear = input.endYear,
                hasEvents = input.events.hasItems(),
                hasStories = input.stories.hasItems(),
                hasCharacters = input.characters.hasItems(),
                hasCreators = input.creators.hasItems(),
                hasSeries = false,
                hasComics = input.comics.hasItems()
            )
        }
    }
}