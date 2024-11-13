package com.wongislandd.infinityindex.infra.paging

import app.cash.paging.PagingSource
import app.cash.paging.PagingSourceLoadParams
import app.cash.paging.PagingSourceLoadResult
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import app.cash.paging.PagingState
import com.wongislandd.infinityindex.infra.networking.models.DataWrapper
import com.wongislandd.infinityindex.infra.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BasePagingSource<Value : Any> : PagingSource<Int, Value>() {

    private val _isFetchingFirstPage: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isFetchingFirstPage: StateFlow<Boolean> = _isFetchingFirstPage

    protected abstract suspend fun fetchData(start: Int, count: Int): Resource<DataWrapper<Value>>

    override suspend fun load(params: PagingSourceLoadParams<Int>): PagingSourceLoadResult<Int, Value> {
        val start = params.key ?: 0
        val limit = params.loadSize
        return try {
            val response = fetchData(start, limit)
            _isFetchingFirstPage.update { false }
            when (val page = paginateResponse(response)) {
                is Resource.Success -> {
                    val nextOffset = page.data.start + page.data.count
                    PagingSourceLoadResultPage(
                        data = page.data.items,
                        prevKey = null,
                        nextKey = if (nextOffset + params.loadSize <= page.data.total) {
                            nextOffset + params.loadSize
                        } else {
                            null
                        }
                    )
                }
                is Resource.Error -> {
                    return PagingSourceLoadResultError(Exception(page.error.toString()))
                }
                else -> {
                    return PagingSourceLoadResultError(Exception("Unknown error"))
                }
            }
        } catch (e: Exception) {
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