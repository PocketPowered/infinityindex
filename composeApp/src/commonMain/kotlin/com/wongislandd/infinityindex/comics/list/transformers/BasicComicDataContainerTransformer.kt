package com.wongislandd.infinityindex.comics.list.transformers

import com.wongislandd.infinityindex.comics.list.models.BasicComicDataContainer
import com.wongislandd.infinityindex.comics.list.models.NetworkComicDataContainer
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

class BasicComicDataContainerTransformer(
    private val basicComicTransformer: BasicComicTransformer
) : Transformer<NetworkComicDataContainer, BasicComicDataContainer> {
    override fun transform(input: NetworkComicDataContainer): BasicComicDataContainer? {
        val transformedResults = input.results?.mapNotNull {
            basicComicTransformer.transform(it)
        } ?: emptyList()
        return safeLet(
            input.offset,
            input.limit,
            input.total,
            input.count,
        ) { offset, limit, total, count ->
            BasicComicDataContainer(
                offset = offset,
                limit = limit,
                total = total,
                count = count,
                results = transformedResults
            )
        }
    }
}