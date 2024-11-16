package com.wongislandd.infinityindex.entities.comics.transformers

import com.wongislandd.infinityindex.entities.comics.models.RelatedPrice
import com.wongislandd.infinityindex.entities.comics.NetworkFieldTypeMapper
import com.wongislandd.infinityindex.models.network.NetworkComicPrice
import com.wongislandd.infinityindex.infra.util.Transformer
import com.wongislandd.infinityindex.infra.util.safeLet

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