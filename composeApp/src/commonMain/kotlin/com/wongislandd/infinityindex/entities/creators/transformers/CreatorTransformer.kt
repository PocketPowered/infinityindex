package com.wongislandd.infinityindex.entities.creators.transformers

import com.wongislandd.infinityindex.networking.util.ImageUrlTransformer
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.creators.models.NetworkCreator
import com.wongislandd.infinityindex.util.DataWrapperTransformer
import com.wongislandd.infinityindex.util.safeLet

class CreatorTransformer(
    private val imageUrlTransformer: ImageUrlTransformer,
) : DataWrapperTransformer<NetworkCreator, Creator>() {
    override fun itemTransformer(input: NetworkCreator): Creator? {
        return safeLet(
            input.id,
            input.thumbnail,
            input.fullName,
        ) { id, thumbnail, name ->
            Creator(
                id = id,
                displayName = name,
                imageUrl = imageUrlTransformer.transform(thumbnail),
            )
        }
    }
}