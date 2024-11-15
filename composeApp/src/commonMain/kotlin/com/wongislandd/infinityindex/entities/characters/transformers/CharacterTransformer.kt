package com.wongislandd.infinityindex.entities.characters.transformers

import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.characters.models.NetworkCharacter
import com.wongislandd.infinityindex.entities.comics.details.transformers.RelatedLinksTransformer
import com.wongislandd.infinityindex.infra.models.DefaultImageType
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.navigation.RouteHelper
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.infinityindex.infra.util.safeLet

class CharacterTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
    private val relatedLinksTransformer: RelatedLinksTransformer,
) : DataWrapperTransformer<NetworkCharacter, Character>() {
    override fun itemTransformer(input: NetworkCharacter): Character? {
        val relatedLinks = input.urls?.let {
            relatedLinksTransformer.transform(it)
        } ?: emptyList()
        return safeLet(
            input.id,
            input.name,
        ) { id, name ->
            Character(
                id = id,
                displayName = name,
                image = loadableImageTransformer.transform(
                    LoadableImageTransformerInput(
                        networkImage = input.thumbnail,
                        defaultImageType = DefaultImageType.PERSON
                    )
                ),
                navContext = NavigationContext(
                    RouteHelper.getCharacterDetailsRouteForId(id)
                ),
                description = input.description,
                modified = input.modified,
                relatedLinks = relatedLinks,
            )
        }
    }
}