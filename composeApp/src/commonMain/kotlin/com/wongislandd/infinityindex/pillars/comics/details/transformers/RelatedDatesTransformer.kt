package com.wongislandd.infinityindex.pillars.comics.details.transformers

import com.wongislandd.infinityindex.pillars.comics.details.helpers.NetworkFieldTypeMapper
import com.wongislandd.infinityindex.pillars.comics.details.models.RelatedDate
import com.wongislandd.infinityindex.pillars.comics.list.models.NetworkComicDate
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

class RelatedDatesTransformer(
    private val networkFieldTypeMapper: NetworkFieldTypeMapper,
    private val dateTransformer: com.wongislandd.infinityindex.pillars.comics.list.transformers.DateTransformer
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