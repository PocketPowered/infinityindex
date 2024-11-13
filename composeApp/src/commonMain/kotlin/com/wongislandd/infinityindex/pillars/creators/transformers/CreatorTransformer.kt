package com.wongislandd.infinityindex.pillars.creators.transformers

import com.wongislandd.infinityindex.networking.util.ImageUrlTransformer
import com.wongislandd.infinityindex.pillars.creators.models.Creator
import com.wongislandd.infinityindex.pillars.creators.models.NetworkCreator
import com.wongislandd.infinityindex.pillars.comics.details.transformers.RelatedLinksTransformer
import com.wongislandd.infinityindex.util.DataWrapperTransformer
import com.wongislandd.infinityindex.util.safeLet

class CreatorTransformer(
    private val imageUrlTransformer: ImageUrlTransformer,
    private val relatedLinksTransformer: RelatedLinksTransformer,
) : DataWrapperTransformer<NetworkCreator, Creator>() {
    override fun itemTransformer(input: NetworkCreator): Creator? {
        val relatedLinks = input.urls?.let {
            relatedLinksTransformer.transform(it)
        } ?: emptyList()
        return safeLet(
            input.id,
            input.thumbnail,
            input.name,
        ) { id, thumbnail, name ->
            Creator(
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