package com.wongislandd.infinityindex.networking

import com.wongislandd.infinityindex.comics.list.transformers.ComicDataContainerTransformer
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

class DataWrapperTransformer(
    private val comicDataContainerTransformer: ComicDataContainerTransformer
): Transformer<NetworkMarvelDataWrapper, MarvelDataWrapper> {

    override fun transform(input: NetworkMarvelDataWrapper): MarvelDataWrapper? {
        val transformedData = input.data?.let {
            comicDataContainerTransformer.transform(it)
        }
        return safeLet(input.code, input.status, transformedData) { code, status, data ->
            MarvelDataWrapper(
                code = code,
                status = status,
                data = data
            )
        }
    }
}