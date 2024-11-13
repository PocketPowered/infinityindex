package com.wongislandd.infinityindex.entities.comics.details.transformers

import com.wongislandd.infinityindex.entities.comics.details.models.RelatedPrice
import com.wongislandd.infinityindex.entities.comics.details.helpers.NetworkFieldTypeMapper
import com.wongislandd.infinityindex.entities.comics.list.models.NetworkComicPrice
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

class RelatedPricesTransformer(
    private val networkFieldTypeMapper: NetworkFieldTypeMapper
) : Transformer<List<NetworkComicPrice>, List<RelatedPrice>> {

    override fun transform(input: List<NetworkComicPrice>): List<RelatedPrice> {
        return input.mapNotNull { networkUrl ->
            val associatedType = networkUrl.type?.let { networkFieldTypeMapper.mapPriceType(it) }
            safeLet(associatedType, networkUrl.price) { type, price ->
                RelatedPrice(type, price)
            }
        }
    }

}