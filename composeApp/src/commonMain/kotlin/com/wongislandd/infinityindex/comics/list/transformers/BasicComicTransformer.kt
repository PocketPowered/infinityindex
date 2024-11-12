package com.wongislandd.infinityindex.comics.list.transformers

import com.wongislandd.infinityindex.comics.list.models.BasicComic
import com.wongislandd.infinityindex.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

class BasicComicTransformer(
    private val dateTransformer: DateTransformer,
    private val imageUrlTransformer: ImageUrlTransformer
) : Transformer<NetworkComic, BasicComic> {

    override fun transform(input: NetworkComic): BasicComic? {
        return safeLet(
            input.id,
            input.thumbnail,
            input.title,
        ) { id, thumbnail, title ->
            BasicComic(
                id = id,
                imageUrl = imageUrlTransformer.transform(thumbnail),
                title = title,
//                subtitle = dateTransformer.transform(lastModifiedDate)
            )
        }
    }
}