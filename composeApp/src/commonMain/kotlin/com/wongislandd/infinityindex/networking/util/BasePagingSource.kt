package com.wongislandd.infinityindex.networking.util

import app.cash.paging.PagingSource
import app.cash.paging.PagingSourceLoadParams
import app.cash.paging.PagingSourceLoadResult
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import app.cash.paging.PagingState

abstract class BasePagingSource<Value : Any> : PagingSource<Int, Value>() {

    protected abstract suspend fun fetchData(start: Int, count: Int): Resource<PaginationItems<Value>>

    override suspend fun load(params: PagingSourceLoadParams<Int>): PagingSourceLoadResult<Int, Value> {
        val start = params.key ?: 0
        val limit = params.loadSize
        return try {
            when (val response = fetchData(start, limit)) {
                is Resource.Success -> {
                    val nextOffset = response.data.start + response.data.count
                    PagingSourceLoadResultPage(
                        data = response.data.items,
                        prevKey = null,
                        nextKey = if (nextOffset + params.loadSize <= response.data.total) {
                            nextOffset + params.loadSize
                        } else {
                            null
                        }
                    )
                }
                is Resource.Error -> {
                    return PagingSourceLoadResultError(Exception(response.error.toString()))
                }
                else -> {
                    return PagingSourceLoadResultError(Exception("Unknown error"))
                }
            }
        } catch (e: Exception) {
            PagingSourceLoadResultError(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition
    }

}