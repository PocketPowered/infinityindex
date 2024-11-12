package com.wongislandd.infinityindex.pillars.comics.details.transformers

import com.wongislandd.infinityindex.pillars.comics.details.models.DetailedComicDataWrapper
import com.wongislandd.infinityindex.pillars.comics.list.models.NetworkComicDataWrapper
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

class DetailedComicDataWrapperTransformer(
    private val detailedComicDataContainerTransformer: DetailedComicDataContainerTransformer
): Transformer<NetworkComicDataWrapper, DetailedComicDataWrapper> {

    override fun transform(input: NetworkComicDataWrapper): DetailedComicDataWrapper? {
        val transformedData = input.data?.let {
            detailedComicDataContainerTransformer.transform(it)
        }
        return safeLet(input.code, input.status, transformedData) { code, status, data ->
            DetailedComicDataWrapper(
                code = code,
                status = status,
                data = data
            )
        }
    }
}