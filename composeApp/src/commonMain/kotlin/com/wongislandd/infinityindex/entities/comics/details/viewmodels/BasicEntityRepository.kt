package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import com.wongislandd.infinityindex.networking.util.DataWrapper
import com.wongislandd.infinityindex.networking.util.Resource

interface BasicEntityRepository <T>{
    suspend fun getAll(
        start: Int,
        count: Int,
        searchParam: String?,
        sortKey: String?
    ): Resource<DataWrapper<T>>

    suspend fun get(
        id: Int
    ): Resource<T>

    suspend fun getPagedEntityFromComic(
        comicId: Int,
        start: Int,
        count: Int
    ): Resource<DataWrapper<T>>


}