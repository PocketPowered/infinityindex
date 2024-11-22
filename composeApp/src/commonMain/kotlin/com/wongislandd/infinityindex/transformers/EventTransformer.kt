package com.wongislandd.infinityindex.transformers

import com.wongislandd.infinityindex.entities.comics.transformers.DateTransformer
import com.wongislandd.infinityindex.infra.models.DefaultImageType
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.navigation.NavigationHelper
import com.wongislandd.infinityindex.infra.networking.models.getAvailableItems
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.safeLet
import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.models.network.NetworkEvent

class EventTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
    private val dateTransformer: DateTransformer
) : DataWrapperTransformer<NetworkEvent, Event>() {
    override fun itemTransformer(input: NetworkEvent): Event? {
        val transformedStart = input.start?.let {
            dateTransformer.transform(input.start)
        }
        val transformedEnd = input.end?.let {
            dateTransformer.transform(input.end)
        }
        return safeLet(
            input.id,
            input.title,
            input.modified?.let { dateTransformer.transform(it) }
        ) { id, title, modified ->
            Event(
                id = id,
                displayName = title,
                navContext = NavigationContext(
                    NavigationHelper.getDetailsRoute(
                        EntityType.EVENTS,
                        id,
                        title
                    )
                ),
                image = loadableImageTransformer.transform(
                    LoadableImageTransformerInput(
                        networkImage = input.thumbnail,
                        defaultImageType = DefaultImageType.PLACE
                    )
                ),
                description = input.description,
                start = transformedStart,
                end = transformedEnd,
                relatedEventsCount = 0,
                relatedStoriesCount = input.stories.getAvailableItems(),
                relatedCharactersCount = input.characters.getAvailableItems(),
                relatedCreatorsCount = input.creators.getAvailableItems(),
                relatedSeriesCount = input.series.getAvailableItems(),
                relatedComicsCount = input.comics.getAvailableItems(),
                lastModified = modified
            )
        }
    }
}