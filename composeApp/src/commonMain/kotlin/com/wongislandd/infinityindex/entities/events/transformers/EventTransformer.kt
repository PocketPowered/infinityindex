package com.wongislandd.infinityindex.entities.events.transformers

import com.wongislandd.infinityindex.infra.transformers.ImageUrlTransformer
import com.wongislandd.infinityindex.entities.events.models.ComicEvent
import com.wongislandd.infinityindex.entities.events.models.NetworkComicEvent
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.util.safeLet

class EventTransformer(
    private val imageUrlTransformer: ImageUrlTransformer,
) : DataWrapperTransformer<NetworkComicEvent, ComicEvent>() {
    override fun itemTransformer(input: NetworkComicEvent): ComicEvent? {
        return safeLet(
            input.id,
            input.thumbnail,
            input.title,
        ) { id, thumbnail, title ->
            ComicEvent(
                id = id,
                displayName = title,
                imageUrl = imageUrlTransformer.transform(thumbnail),
                description = input.description,
                start = input.start,
                end = input.end
            )
        }
    }
}