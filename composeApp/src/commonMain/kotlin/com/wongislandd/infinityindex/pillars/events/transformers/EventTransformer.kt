package com.wongislandd.infinityindex.pillars.events.transformers

import com.wongislandd.infinityindex.networking.util.ImageUrlTransformer
import com.wongislandd.infinityindex.pillars.events.models.ComicEvent
import com.wongislandd.infinityindex.pillars.events.models.NetworkComicEvent
import com.wongislandd.infinityindex.util.DataWrapperTransformer
import com.wongislandd.infinityindex.util.safeLet

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