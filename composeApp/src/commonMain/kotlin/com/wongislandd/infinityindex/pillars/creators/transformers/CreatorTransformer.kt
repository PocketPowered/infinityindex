package com.wongislandd.infinityindex.pillars.creators.transformers

import com.wongislandd.infinityindex.networking.util.ImageUrlTransformer
import com.wongislandd.infinityindex.pillars.creators.models.Creator
import com.wongislandd.infinityindex.pillars.creators.models.NetworkCreator
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
                fullName = name,
                imageUrl = imageUrlTransformer.transform(thumbnail),
            )
        }
    }
}