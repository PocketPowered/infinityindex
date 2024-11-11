package com.wongislandd.infinityindex.comics.list.transformers

import com.wongislandd.infinityindex.comics.list.models.Comic
import com.wongislandd.infinityindex.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

class ComicTransformer(
    private val dateTransformer: DateTransformer,
    private val imageUrlTransformer: ImageUrlTransformer
) : Transformer<NetworkComic, Comic> {

    override fun transform(input: NetworkComic): Comic? {
        return safeLet(
            input.thumbnail,
            input.title,
        ) { thumbnail, title ->
            Comic(
                imageUrl = imageUrlTransformer.transform(thumbnail),
                title = title,
//                subtitle = dateTransformer.transform(lastModifiedDate)
            )
        }
    }
}