package com.wongislandd.infinityindex.util

import com.wongislandd.infinityindex.networking.util.DataContainer
import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.NetworkDataContainer
import com.wongislandd.infinityindex.networking.util.NetworkDataWrapper

abstract class DataWrapperTransformer<T, U> :
    Transformer<NetworkDataWrapper<T>, DataWrapper<U>> {

    abstract fun itemTransformer(input: T): U?

    override fun transform(input: NetworkDataWrapper<T>): DataWrapper<U>? {
        val maybeTransformedDataContainer = transformDataContainer(input.data)

        return safeLet(input.code, input.status, maybeTransformedDataContainer) { code, status, dataContainer ->
            DataWrapper(
                code = code,
                status = status,
                data = dataContainer
            )
        }
    }

    private fun transformDataContainer(networkDataContainer: NetworkDataContainer<T>): DataContainer<U> {
        val transformedResults = networkDataContainer.results?.mapNotNull { item ->
            itemTransformer(item)
        } ?: emptyList()

        return DataContainer(
            offset = networkDataContainer.offset ?: 0,
            limit = networkDataContainer.limit ?: 0,
            total = networkDataContainer.total ?: 0,
            count = networkDataContainer.count ?: 0,
            results = transformedResults
        )
    }
}