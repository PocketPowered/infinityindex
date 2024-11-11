package com.wongislandd.infinityindex.comics.transformers

import com.wongislandd.infinityindex.comics.models.ComicDataWrapper
import com.wongislandd.infinityindex.comics.models.NetworkComicDataWrapper
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

class ComicDataWrapperTransformer(
    private val comicDataContainerTransformer: ComicDataContainerTransformer
): Transformer<NetworkComicDataWrapper, ComicDataWrapper> {

    override fun transform(input: NetworkComicDataWrapper): ComicDataWrapper? {
        val transformedData = input.data?.let {
            comicDataContainerTransformer.transform(it)
        }
        return safeLet(input.code, input.status, transformedData) { code, status, data ->
            ComicDataWrapper(
                code = code,
                status = status,
                data = data
            )
        }
    }
}