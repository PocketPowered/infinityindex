package com.wongislandd.infinityindex.transformers

import com.wongislandd.infinityindex.transformers.util.DateTransformer
import com.wongislandd.infinityindex.transformers.util.RoledCreatorOutput
import com.wongislandd.infinityindex.transformers.util.RoledCreatorTransformer
import com.wongislandd.infinityindex.infra.models.DefaultImageType
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.navigation.NavigationHelper
import com.wongislandd.infinityindex.infra.networking.models.getAvailableItems
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformer
import com.wongislandd.infinityindex.infra.transformers.LoadableImageTransformerInput
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.safeLet
import com.wongislandd.infinityindex.models.local.Story
import com.wongislandd.infinityindex.models.network.NetworkStory

class StoryTransformer(
    private val loadableImageTransformer: LoadableImageTransformer,
    private val dateTransformer: DateTransformer,
    private val roledCreatorTransformer: RoledCreatorTransformer
) : DataWrapperTransformer<NetworkStory, Story>() {
    override fun itemTransformer(input: NetworkStory): Story? {
        val creatorsOutput = input.creators?.let {
            roledCreatorTransformer.transform(it)
        } ?: RoledCreatorOutput(emptyMap(), emptyMap())
        return safeLet(
            input.id,
            input.title,
            input.modified?.let { dateTransformer.transform(it) }
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
                    NavigationHelper.getDetailsRoute(
                        EntityType.STORIES,
                        id,
                        title
                    )
                ),
                type = input.type?.takeIf { it.isNotBlank() },
                description = input.description?.takeIf { it.isNotBlank() },
                relatedEventsCount = input.events.getAvailableItems(),
                relatedStoriesCount = 0,
                relatedCharactersCount = input.characters.getAvailableItems(),
                relatedCreatorsCount = input.creators.getAvailableItems(),
                relatedSeriesCount = input.series.getAvailableItems(),
                relatedComicsCount = input.comics.getAvailableItems(),
                lastModified = modified,
                creatorsByRole = creatorsOutput.primaryCreators
            )
        }
    }
}