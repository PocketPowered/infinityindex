package com.wongislandd.infinityindex.infra.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.cash.paging.PagingSourceLoadParams
import app.cash.paging.PagingSourceLoadResult
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import com.wongislandd.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.infinityindex.infra.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface PagingSourceCallbacks<T> {
    fun onResponse(response: Resource<DataWrapper<T>>)

    fun onSuccess(paginationContextWrapper: PaginationContextWrapper<T>)

    fun onFailure(error: Throwable? = null)
}

abstract class BasePagingSource<Value : Any> : PagingSource<Int, Value>() {

    private val _isFetchingFirstPage: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isFetchingFirstPage: StateFlow<Boolean> = _isFetchingFirstPage

    private var pagingSourceCallbacks: PagingSourceCallbacks<Value>? = null

    fun registerCallbacks(callbacks: PagingSourceCallbacks<Value>) {
        this.pagingSourceCallbacks = callbacks
    }

    protected abstract suspend fun fetchData(start: Int, count: Int): Resource<DataWrapper<Value>>

    override suspend fun load(params: PagingSourceLoadParams<Int>): PagingSourceLoadResult<Int, Value> {
        val start = params.key ?: 0
        val limit = params.loadSize
        return try {
            val response = fetchData(start, limit)
            pagingSourceCallbacks?.onResponse(response)
            when (val page = paginateResponse(response)) {
                is Resource.Success -> {
                    pagingSourceCallbacks?.onSuccess(page.data)
                    val nextOffset = page.data.start + page.data.count
                    val nextKey = if (nextOffset + params.loadSize <= page.data.total) {
                        nextOffset
                    } else {
                        null
                    }
                    PagingSourceLoadResultPage(
                        data = page.data.items,
                        prevKey = null,
                        nextKey = nextKey
                    )
                }

                is Resource.Error -> {
                    pagingSourceCallbacks?.onFailure(page.throwable)
                    return PagingSourceLoadResultError(Exception(page.error.toString()))
                }
                else -> {
                    pagingSourceCallbacks?.onFailure()
                    return PagingSourceLoadResultError(Exception("Unknown error"))
                }
            }
        } catch (e: Exception) {
            pagingSourceCallbacks?.onFailure()
            PagingSourceLoadResultError(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? = null

    private fun paginateResponse(response: Resource<DataWrapper<Value>>): Resource<PaginationContextWrapper<Value>> {
        return response.map { data ->
            PaginationContextWrapper(
                items = data.data.results,
                start = data.data.offset,
                count = data.data.count,
                total = data.data.total.toLong()
            )
        }
    }
}