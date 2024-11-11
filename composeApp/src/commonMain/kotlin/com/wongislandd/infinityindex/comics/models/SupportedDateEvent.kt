package com.wongislandd.infinityindex.comics.models

enum class SupportedDateEvent {
    FOC,
    ON_SALE
}

fun NetworkComicDate.mapToSupportedDateEvent(): SupportedDateEvent? {
    return when (this.type?.lowercase()) {
        "focdate" -> SupportedDateEvent.FOC
        "onsaledate" -> SupportedDateEvent.ON_SALE
        else -> null
    }
}