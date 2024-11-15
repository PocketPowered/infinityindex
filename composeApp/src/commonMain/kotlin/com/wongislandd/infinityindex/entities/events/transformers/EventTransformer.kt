package com.wongislandd.infinityindex.entities.events.transformers

import com.wongislandd.infinityindex.entities.comics.list.transformers.DateTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.events.models.NetworkEvent
import com.wongislandd.infinityindex.infra.models.DefaultImageType
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.navigation.RouteHelper
import com.wongislandd.infinityindex.infra.networking.models.hasItems
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.infinityindex.infra.util.safeLet

class EventTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
    private val dateTransformer: DateTransformer
) : DataWrapperTransformer<NetworkEvent, Event>() {
    override fun itemTransformer(input: NetworkEvent): Event? {
        return safeLet(
            input.id,
            input.title,
            input.modified
        ) { id, title, modified ->
            Event(
                id = id,
                displayName = title,
                navContext = NavigationContext(
                    RouteHelper.getEventDetailsRouteForId(id)
                ),
                image = loadableImageTransformer.transform(
                    LoadableImageTransformerInput(
                        networkImage = input.thumbnail,
                        defaultImageType = DefaultImageType.PLACE
                    )
                ),
                description = input.description,
                start = input.start,
                end = input.end,
                hasEvents = false,
                hasStories = input.stories.hasItems(),
                hasCharacters = input.characters.hasItems(),
                hasCreators = input.creators.hasItems(),
                hasSeries = input.series.hasItems(),
                hasComics = input.comics.hasItems(),
                lastModified = dateTransformer.transform(modified)
            )
        }
    }
}