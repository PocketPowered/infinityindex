package com.wongislandd.infinityindex

abstract class DataWrapper<T, U: DataContainer<T>>(
    val code: Int?,
    val status: String?,
    val data: U,
)