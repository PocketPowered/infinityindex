package com.wongislandd.infinityindex.entities.comics.transformers

import com.wongislandd.infinityindex.entities.comics.models.RelatedText
import com.wongislandd.infinityindex.entities.comics.NetworkFieldTypeMapper
import com.wongislandd.infinityindex.models.network.NetworkTextObject
import com.wongislandd.infinityindex.infra.util.Transformer
import com.wongislandd.infinityindex.infra.util.safeLet

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