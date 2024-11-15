package com.wongislandd.infinityindex.entities.stories

import com.wongislandd.infinityindex.entities.comics.list.transformers.DateTransformer
import com.wongislandd.infinityindex.entities.stories.models.NetworkStory
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.models.DefaultImageType
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.navigation.RouteHelper
import com.wongislandd.infinityindex.infra.networking.models.getAvailableItems
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.infinityindex.infra.util.safeLet

class StoryTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
    private val dateTransformer: DateTransformer
) : DataWrapperTransformer<NetworkStory, Story>() {
    override fun itemTransformer(input: NetworkStory): Story? {
        return safeLet(
            input.id,
            input.title,
            input.modified
        ) { id, title, modified ->
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
                relatedEventsCount = input.events.getAvailableItems(),
                relatedStoriesCount = 0,
                relatedCharactersCount = input.characters.getAvailableItems(),
                relatedCreatorsCount = input.creators.getAvailableItems(),
                relatedSeriesCount = input.series.getAvailableItems(),
                relatedComicsCount = input.comics.getAvailableItems(),
                lastModified = dateTransformer.transform(modified)
            )
        }
    }
}