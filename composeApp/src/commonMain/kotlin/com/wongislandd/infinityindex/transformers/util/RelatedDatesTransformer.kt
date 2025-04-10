package com.wongislandd.infinityindex.transformers.util

import com.wongislandd.infinityindex.models.util.RelatedDate
import com.wongislandd.infinityindex.models.network.NetworkComicDate
import com.wongislandd.infinityindex.infra.util.Transformer
import com.wongislandd.infinityindex.infra.util.safeLet

class RelatedDatesTransformer(
    private val networkFieldTypeMapper: NetworkFieldTypeMapper,
    private val dateTransformer: DateTransformer
) : Transformer<List<NetworkComicDate>, List<RelatedDate>> {

    override fun transform(input: List<NetworkComicDate>): List<RelatedDate> {
        return input.mapNotNull { networkDate ->
            val associatedType = networkDate.type?.let { networkFieldTypeMapper.mapDateType(it) }
            val transformedDate = networkDate.date?.let { dateTransformer.transform(it) }
            safeLet(associatedType, transformedDate) { type, date ->
                RelatedDate(type, date)
            }
        }
    }

}