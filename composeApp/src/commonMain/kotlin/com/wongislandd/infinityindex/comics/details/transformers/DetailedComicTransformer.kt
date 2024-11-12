package com.wongislandd.infinityindex.comics.details.transformers

import com.wongislandd.infinityindex.comics.details.models.DetailedComic
import com.wongislandd.infinityindex.comics.list.models.BasicComic
import com.wongislandd.infinityindex.comics.list.models.NetworkComic
import com.wongislandd.infinityindex.comics.list.transformers.DateTransformer
import com.wongislandd.infinityindex.comics.list.transformers.ImageUrlTransformer
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

class DetailedComicTransformer(
    private val dateTransformer: DateTransformer,
    private val imageUrlTransformer: ImageUrlTransformer
) : Transformer<NetworkComic, DetailedComic> {

    override fun transform(input: NetworkComic): DetailedComic? {
        return safeLet(
            input.thumbnail,
            input.title,
        ) { thumbnail, title ->
            DetailedComic(
                title = title,
                imageUrl = imageUrlTransformer.transform(thumbnail),
            )
        }
    }
}