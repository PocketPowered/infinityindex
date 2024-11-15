package com.wongislandd.infinityindex.entities.stories

import com.wongislandd.infinityindex.entities.stories.models.NetworkStory
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.models.DefaultImageType
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.navigation.RouteHelper
import com.wongislandd.infinityindex.infra.networking.models.hasItems
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.infinityindex.infra.util.safeLet

class StoryTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
) : DataWrapperTransformer<NetworkStory, Story>() {
    override fun itemTransformer(input: NetworkStory): Story? {
        return safeLet(
            input.id,
            input.title,
        ) { id, title ->
            Story(
                id = id,
                displayName = title,
                image = loadableImageTransformer.transform(
                    LoadableImageTransformerInput(
                        networkImage = input.thumbnail,
                        defaultImageType = DefaultImageType.BOOK
                    )
                ),
                navContext = NavigationContext(
                    RouteHelper.getStoryDetailsRouteForId(id)
                ),
                type = input.type?.takeIf { it.isNotBlank() },
                description = input.description?.takeIf { it.isNotBlank() },
                hasEvents = input.events.hasItems(),
                hasStories = false,
                hasCharacters = input.characters.hasItems(),
                hasCreators = input.creators.hasItems(),
                hasSeries = input.series.hasItems(),
                hasComics = input.comics.hasItems()
            )
        }
    }
}