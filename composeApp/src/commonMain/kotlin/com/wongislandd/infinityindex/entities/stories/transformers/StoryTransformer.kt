package com.wongislandd.infinityindex.entities.stories.transformers

import com.wongislandd.infinityindex.infra.transformers.ImageUrlTransformer
import com.wongislandd.infinityindex.entities.stories.models.NetworkStory
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.transformers.DataWrapperTransformer
import com.wongislandd.infinityindex.infra.util.safeLet

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