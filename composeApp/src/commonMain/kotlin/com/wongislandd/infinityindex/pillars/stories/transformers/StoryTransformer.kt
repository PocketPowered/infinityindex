package com.wongislandd.infinityindex.pillars.stories.transformers

import com.wongislandd.infinityindex.networking.util.ImageUrlTransformer
import com.wongislandd.infinityindex.pillars.stories.models.NetworkStory
import com.wongislandd.infinityindex.pillars.stories.models.Story
import com.wongislandd.infinityindex.util.DataWrapperTransformer
import com.wongislandd.infinityindex.util.safeLet

class StoryTransformer(
    private val imageUrlTransformer: ImageUrlTransformer,
) : DataWrapperTransformer<NetworkStory, Story>() {
    override fun itemTransformer(input: NetworkStory): Story? {
        return safeLet(
            input.id,
            input.thumbnail,
            input.title,
        ) { id, thumbnail, title ->
            Story(
                id = id,
                displayName = title,
                imageUrl = imageUrlTransformer.transform(thumbnail),
                type = input.type,
                description = input.description,
            )
        }
    }
}