package com.wongislandd.infinityindex.entities.comics.details.transformers

import com.wongislandd.infinityindex.entities.comics.details.models.RelatedText
import com.wongislandd.infinityindex.entities.comics.details.helpers.NetworkFieldTypeMapper
import com.wongislandd.infinityindex.entities.comics.list.models.NetworkTextObject
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

class RelatedTextsTransformer(
    private val networkFieldTypeMapper: NetworkFieldTypeMapper
) : Transformer<List<NetworkTextObject>, List<RelatedText>> {

    override fun transform(input: List<NetworkTextObject>): List<RelatedText> {
        return input.mapNotNull { networkUrl ->
            val associatedType = networkUrl.type?.let { networkFieldTypeMapper.mapTextType(it) }
            safeLet(associatedType, networkUrl.text) { type, text ->
                RelatedText(type, text)
            }
        }
    }

}