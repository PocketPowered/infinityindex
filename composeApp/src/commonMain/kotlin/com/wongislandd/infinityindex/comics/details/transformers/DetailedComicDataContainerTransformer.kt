package com.wongislandd.infinityindex.comics.details.transformers

import com.wongislandd.infinityindex.comics.details.models.DetailedComicDataContainer
import com.wongislandd.infinityindex.comics.list.models.BasicComicDataContainer
import com.wongislandd.infinityindex.comics.list.models.NetworkComicDataContainer
import com.wongislandd.infinityindex.comics.list.transformers.BasicComicTransformer
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

class DetailedComicDataContainerTransformer(
    private val detailedComicTransformer: DetailedComicTransformer
) : Transformer<NetworkComicDataContainer, DetailedComicDataContainer> {
    override fun transform(input: NetworkComicDataContainer): DetailedComicDataContainer? {
        val transformedResults = input.results?.mapNotNull {
            detailedComicTransformer.transform(it)
        } ?: emptyList()
        return safeLet(
            input.offset,
            input.limit,
            input.total,
            input.count,
        ) { offset, limit, total, count ->
            DetailedComicDataContainer(
                offset = offset,
                limit = limit,
                total = total,
                count = count,
                results = transformedResults
            )
        }
    }
}