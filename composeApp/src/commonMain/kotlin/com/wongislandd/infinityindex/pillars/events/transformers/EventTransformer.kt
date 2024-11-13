package com.wongislandd.infinityindex.pillars.events.transformers

import com.wongislandd.infinityindex.networking.util.ImageUrlTransformer
import com.wongislandd.infinityindex.pillars.events.models.Event
import com.wongislandd.infinityindex.pillars.events.models.NetworkEvent
import com.wongislandd.infinityindex.pillars.comics.details.transformers.RelatedLinksTransformer
import com.wongislandd.infinityindex.util.DataWrapperTransformer
import com.wongislandd.infinityindex.util.safeLet

class EventTransformer(
    private val imageUrlTransformer: ImageUrlTransformer,
    private val relatedLinksTransformer: RelatedLinksTransformer,
) : DataWrapperTransformer<NetworkEvent, Event>() {
    override fun itemTransformer(input: NetworkEvent): Event? {
        val relatedLinks = input.urls?.let {
            relatedLinksTransformer.transform(it)
        } ?: emptyList()
        return safeLet(
            input.id,
            input.thumbnail,
            input.name,
        ) { id, thumbnail, name ->
            Event(
                id = id,
                name = name,
                imageUrl = imageUrlTransformer.transform(thumbnail),
                description = input.description,
                modified = input.modified,
                relatedLinks = relatedLinks,
            )
        }
    }
}