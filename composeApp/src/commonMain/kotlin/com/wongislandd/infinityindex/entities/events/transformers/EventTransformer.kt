package com.wongislandd.infinityindex.entities.events.transformers

import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.events.models.NetworkComicEvent
import com.wongislandd.infinityindex.infra.models.DefaultImageType
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.navigation.RouteHelper
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.infinityindex.infra.util.safeLet

class EventTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
) : DataWrapperTransformer<NetworkComicEvent, Event>() {
    override fun itemTransformer(input: NetworkComicEvent): Event? {
        return safeLet(
            input.id,
            input.title,
        ) { id, title ->
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
                end = input.end
            )
        }
    }
}