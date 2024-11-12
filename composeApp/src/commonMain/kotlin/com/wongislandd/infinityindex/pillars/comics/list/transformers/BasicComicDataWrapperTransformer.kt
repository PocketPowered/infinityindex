package com.wongislandd.infinityindex.pillars.comics.list.transformers

import com.wongislandd.infinityindex.pillars.comics.list.models.BasicComicDataWrapper
import com.wongislandd.infinityindex.pillars.comics.list.models.NetworkComicDataWrapper
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

class BasicComicDataWrapperTransformer(
    private val basicComicDataContainerTransformer: BasicComicDataContainerTransformer
): Transformer<NetworkComicDataWrapper, BasicComicDataWrapper> {

    override fun transform(input: NetworkComicDataWrapper): BasicComicDataWrapper? {
        val transformedData = input.data?.let {
            basicComicDataContainerTransformer.transform(it)
        }
        return safeLet(input.code, input.status, transformedData) { code, status, data ->
            BasicComicDataWrapper(
                code = code,
                status = status,
                data = data
            )
        }
    }
}