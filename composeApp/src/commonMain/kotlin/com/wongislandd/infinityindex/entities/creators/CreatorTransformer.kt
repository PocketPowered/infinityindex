package com.wongislandd.infinityindex.entities.creators

import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.creators.models.NetworkCreator
import com.wongislandd.infinityindex.infra.models.DefaultImageType
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.navigation.RouteHelper
import com.wongislandd.infinityindex.infra.networking.models.hasItems
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.infinityindex.infra.util.safeLet

class CreatorTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
) : DataWrapperTransformer<NetworkCreator, Creator>() {
    override fun itemTransformer(input: NetworkCreator): Creator? {
        return safeLet(
            input.id,
            input.fullName,
        ) { id, name ->
            Creator(
                id = id,
                displayName = name,
                navContext = NavigationContext(
                    RouteHelper.getCreatorDetailsRouteForId(id)
                ),
                image = loadableImageTransformer.transform(
                    LoadableImageTransformerInput(
                        networkImage = input.thumbnail,
                        defaultImageType = DefaultImageType.PERSON
                    ),
                ),
                hasEvents = input.events.hasItems(),
                hasStories = input.stories.hasItems(),
                hasCharacters = false,
                hasCreators = false,
                hasSeries = input.series.hasItems(),
                hasComics = input.comics.hasItems()
            )
        }
    }
}