package com.wongislandd.infinityindex.comics.details.transformers

import com.wongislandd.infinityindex.comics.details.models.RelatedDate
import com.wongislandd.infinityindex.comics.details.viewmodels.NetworkFieldTypeMapper
import com.wongislandd.infinityindex.comics.list.models.NetworkComicDate
import com.wongislandd.infinityindex.comics.list.transformers.DateTransformer
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

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