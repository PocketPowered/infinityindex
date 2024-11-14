package com.wongislandd.infinityindex.infra.models

data class LoadableImage(
    val imageUrl: String?,
    val defaultEntity: DefaultImageType
)

enum class DefaultImageType {
    PERSON,
    PLACE,
    THING,
    BOOK
}