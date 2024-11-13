package com.wongislandd.infinityindex.entities.comics.details.helpers

import com.wongislandd.infinityindex.entities.comics.details.models.DateType
import com.wongislandd.infinityindex.entities.comics.details.models.LinkType
import com.wongislandd.infinityindex.entities.comics.details.models.PriceType
import com.wongislandd.infinityindex.entities.comics.details.models.TextType

class NetworkFieldTypeMapper {

    private val dateTypeMap = mapOf(
        "onsaleDate" to DateType.ONSALE_DATE,
        "focDate" to DateType.FOC_DATE,
        "unlimitedDate" to DateType.UNLIMITED_DATE,
        "digitalPurchaseDate" to DateType.DIGITAL_PURCHASE_DATE
    )

    private val textTypeMap = mapOf(
        "issue_solicit_text" to TextType.ISSUE_SOLICIT_TEXT
    )

    private val priceTypeMap = mapOf(
        "printPrice" to PriceType.PRINT_PRICE,
        "digitalPurchasePrice" to PriceType.DIGITAL_PURCHASE_PRICE
    )

    private val linkTypeMap = mapOf(
        "detail" to LinkType.DETAILS,
        "purchase" to LinkType.PURCHASE,
        "reader" to LinkType.READER,
        "inAppLink" to LinkType.IN_APP_LINK
    )

    fun mapDateType(dateType: String): DateType? {
        return dateTypeMap.getOrElse(dateType) { null }
    }

    fun mapTextType(textType: String): TextType? {
        return textTypeMap.getOrElse(textType) { null }
    }

    fun mapPriceType(priceType: String): PriceType? {
        return priceTypeMap.getOrElse(priceType) { null }
    }

    fun mapLinkType(linkType: String): LinkType? {
        return linkTypeMap.getOrElse(linkType) { null }
    }
}