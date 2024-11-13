package com.wongislandd.infinityindex.entities.characters.transformers

import com.wongislandd.infinityindex.infra.transformers.ImageUrlTransformer
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.characters.models.NetworkCharacter
import com.wongislandd.infinityindex.entities.comics.details.transformers.RelatedLinksTransformer
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.util.safeLet

class CharacterTransformer(
    private val imageUrlTransformer: ImageUrlTransformer,
    private val relatedLinksTransformer: RelatedLinksTransformer,
) : DataWrapperTransformer<NetworkCharacter, Character>() {
    override fun itemTransformer(input: NetworkCharacter): Character? {
        val relatedLinks = input.urls?.let {
            relatedLinksTransformer.transform(it)
        } ?: emptyList()
        return safeLet(
            input.id,
            input.thumbnail,
            input.name,
        ) { id, thumbnail, name ->
            Character(
                id = id,
                displayName = name,
                imageUrl = imageUrlTransformer.transform(thumbnail),
                description = input.description,
                modified = input.modified,
                relatedLinks = relatedLinks,
            )
        }
    }
}