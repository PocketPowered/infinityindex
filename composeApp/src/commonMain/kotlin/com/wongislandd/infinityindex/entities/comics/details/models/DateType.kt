package com.wongislandd.infinityindex.entities.comics.details.models

enum class DateType(
    val displayName: String
) {
    ONSALE_DATE("On Sale"),
    FOC_DATE("FOC"),
    UNLIMITED_DATE("Unlimited Release"),
    DIGITAL_PURCHASE_DATE("Digital Purchase"),
}