package com.wongislandd.infinityindex.entities.events.transformers

import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.entities.events.models.ComicEvent
import com.wongislandd.infinityindex.entities.events.models.NetworkComicEvent
import com.wongislandd.infinityindex.infra.models.DefaultImageType
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.infinityindex.infra.util.safeLet

class EventTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
) : DataWrapperTransformer<NetworkComicEvent, ComicEvent>() {
    override fun itemTransformer(input: NetworkComicEvent): ComicEvent? {
        return safeLet(
            input.id,
            input.title,
        ) { id, title ->
            ComicEvent(
                id = id,
                displayName = title,
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