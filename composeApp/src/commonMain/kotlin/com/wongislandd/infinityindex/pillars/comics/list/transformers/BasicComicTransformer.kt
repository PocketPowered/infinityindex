package com.wongislandd.infinityindex.pillars.comics.list.transformers

import com.wongislandd.infinityindex.util.DataWrapperTransformer
import com.wongislandd.infinityindex.pillars.comics.list.models.BasicComic
import com.wongislandd.infinityindex.pillars.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.util.safeLet

class BasicComicTransformer(
    private val imageUrlTransformer: ImageUrlTransformer
) : DataWrapperTransformer<NetworkComic, BasicComic>() {

    override fun itemTransformer(input: NetworkComic): BasicComic? {
        return safeLet(
            input.id,
            input.thumbnail,
            input.title,
        ) { id, thumbnail, title ->
            BasicComic(
                id = id,
                imageUrl = imageUrlTransformer.transform(thumbnail),
                title = title
            )
        }
    }
}