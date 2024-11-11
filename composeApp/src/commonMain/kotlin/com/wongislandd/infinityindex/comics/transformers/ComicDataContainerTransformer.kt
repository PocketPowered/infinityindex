package com.wongislandd.infinityindex.comics.transformers

import com.wongislandd.infinityindex.comics.models.ComicDataContainer
import com.wongislandd.infinityindex.comics.models.NetworkComicDataContainer
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

class ComicDataContainerTransformer(private val comicTransformer: ComicTransformer) :
    Transformer<NetworkComicDataContainer, ComicDataContainer> {
    override fun transform(input: NetworkComicDataContainer): ComicDataContainer? {
        val transformedResults = input.results?.mapNotNull {
            comicTransformer.transform(it)
        } ?: emptyList()
        return safeLet(
            input.offset,
            input.limit,
            input.total,
            input.count,
        ) { offset, limit, total, count ->
            ComicDataContainer(
                offset = offset,
                limit = limit,
                total = total,
                count = count,
                results = transformedResults
            )
        }
    }
}